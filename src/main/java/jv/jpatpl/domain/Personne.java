package jv.jpatpl.domain;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "UT_Personne")
@SequenceGenerator(name = Personne.SEQUENCE, sequenceName = Personne.SEQUENCE)
public class Personne extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;
	public static final String SEQUENCE = "ut_personne_sequence";

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String nom;
	private String prenom;

	public Personne(Long id, String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

}
