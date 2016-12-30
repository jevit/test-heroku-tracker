package jv.jpatpl.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE = "CustomGenerator";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private PK id;

	@Version
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		if (getId() == null || other.getId() == null) {
			return false;
		}
		if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append("[");
		PK id = this.getId();
		if (id != null) {
			builder.append(id.toString());
		} else {
			builder.append("undefined");
		}
		builder.append("]");
		return builder.toString();
	}

	public boolean isCreation() {
		return version == null;
	}
}
