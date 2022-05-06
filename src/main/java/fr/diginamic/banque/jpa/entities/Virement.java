package fr.diginamic.banque.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="virement")
@NamedQueries({ 
	@NamedQuery(
			name = "Virement.getComptes", 
			query = "select o from Operation o where o.operationVirement.id = :id"
			)
	})
public class Virement {
	
	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */

	@Column(name = "benificiaire", length = 100, nullable = false)
	private String benificiaire;
	
	

	public Virement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBenificiaire() {
		return benificiaire;
	}

	public void setBenificiaire(String benificiaire) {
		this.benificiaire = benificiaire;
	}
	
	
	
}
