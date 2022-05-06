package fr.diginamic.banque.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="operation")
@NamedQueries({ 
	@NamedQuery(
			name = "Operation.getComptes", 
			query = "select c from Compte c where c.compteOperation.id = :id"
			)
	})
public class Operation {
	
	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	
	@Column(name = "montant", length = 100, nullable = false)
	private Double montant;
	
	@Column(name = "motif", length = 100, nullable = false)
	private String motif;
	
	@ManyToOne
	@JoinColumn(name = "ID_VIR", nullable = true)
	private Virement operationVirement;
	

	public Virement getOperationVirement() {
		return operationVirement;
	}

	public void setOperationVirement(Virement operationVirement) {
		this.operationVirement = operationVirement;
	}

	public Operation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	

}
