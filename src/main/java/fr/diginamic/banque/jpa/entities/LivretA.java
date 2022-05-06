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
@Table(name="livreta")
@NamedQueries({ 
	@NamedQuery(
			name = "LivretA.getComptes", 
			query = "select c from Compte c where c.compteLivret.id = :id"
			)
	})
public class LivretA {
	
	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */

	@Column(name = "taux", length = 100, nullable = false)
	private Double taux;
	
	

	public LivretA() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
	
	
}
