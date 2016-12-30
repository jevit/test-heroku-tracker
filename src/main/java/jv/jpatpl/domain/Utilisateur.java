package jv.jpatpl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "UT_Utilisateur")
@SequenceGenerator(name = Utilisateur.SEQUENCE, sequenceName = Utilisateur.SEQUENCE)
public class Utilisateur extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final String SEQUENCE = "ut_utilisateur_sequence";

	private boolean enabled = true;
	@Column
	private String lastname;

	@Column
	private String firstname;

	@Column
	private String login = null;

	@Column
	private String email = null;

	@Column
	private Date creatingDatetime = null;

	@Column
	private Date updatingDatetime = null;

	@Column
	private Date leavingDatetime = null;

	@Column
	private String password = null;

	// @OptimisticLock(excluded = true)
	// @ManyToMany(cascade = CascadeType.REFRESH)
	// @JoinTable(name = "inpa_role_utilisateur", joinColumns = @JoinColumn(name
	// = "utilisateur_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	// private Set<Role> listeRoles = null;

	//
	// @OptimisticLock(excluded = true)
	// @OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REFRESH},
	// mappedBy="utilisateur")
	// private Set<Parcours> listeParcours = null;

	// public Set<Role> getListeRoles() {
	// return listeRoles;
	// }
	//
	// public void setListeRoles(Set<Role> listeRoles) {
	// this.listeRoles = listeRoles;
	// }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public Set<Parcours> getListeParcours() {
	// return listeParcours;
	// }
	//
	// public void setListeParcours(Set<Parcours> listeParcours) {
	// this.listeParcours = listeParcours;
	// }

	public Utilisateur() {
		// listeParcours = new HashSet<Parcours>();
		// listeRoles = new HashSet<Role>();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append("[");
		builder.append(this.getId() != null ? this.getId() : "null");
		builder.append(",");
		builder.append(this.getEmail() != null ? this.getEmail() : "null");
		builder.append(",");
		builder.append(this.getFirstname() != null ? this.getFirstname() : "null");
		builder.append(",");
		builder.append(this.getLastname() != null ? this.getLastname() : "null");
		builder.append(",");
		builder.append(this.getLogin() != null ? this.getLogin() : "null");
		builder.append(",");
		builder.append(this.getPassword() != null ? this.getPassword() : "null");
		builder.append(",");
		// builder.append(this.getListeRoles() != null ? this.getListeRoles()
		// : "null");

		// Iterator<Parcours> itParcours = this.getListeParcours().iterator();
		// while(itParcours.hasNext()){
		// builder.append(itParcours.next().getId());
		// }
		builder.append("]");
		return builder.toString();
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatingDatetime() {
		return creatingDatetime;
	}

	public void setCreatingDatetime(Date creatingDatetime) {
		this.creatingDatetime = creatingDatetime;
	}

	public Date getUpdatingDatetime() {
		return updatingDatetime;
	}

	public void setUpdatingDatetime(Date updatingDatetime) {
		this.updatingDatetime = updatingDatetime;
	}

	public Date getLeavingDatetime() {
		return leavingDatetime;
	}

	public void setLeavingDatetime(Date leavingDatetime) {
		this.leavingDatetime = leavingDatetime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
