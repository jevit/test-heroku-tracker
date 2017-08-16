package shared;

import java.io.Serializable;
import java.util.Date;

public class CfgRapport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -390143507579688601L;
	private Date dateCreation;
	private String titre;

	public CfgRapport(Date dateCreation, String titre) {
		super();
		this.dateCreation = dateCreation;
		this.titre = titre;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		return "CfgRapport [dateCreation=" + dateCreation + ", titre=" + titre + "]";
	}

}
