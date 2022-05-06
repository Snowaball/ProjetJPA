package fr.diginamic.banque.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="compte")
public class Compte {

	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */
	
	@Column(name = "numero", length = 100, nullable = false)
	private String numero;
	
	@Column(name = "sold", nullable = false)
	private Double sold;
	

	/**
	 * FK entre Emprunt et Client
	 */
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "REL_COM_CLI",
			joinColumns = @JoinColumn(name = "ID_COM", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name="ID_CLI", referencedColumnName = "ID"))
	private Set<Client> comptesClient;
	
	@ManyToOne
	@JoinColumn(name = "ID_LIVRET", nullable = true)
	private LivretA compteLivret;
	
	@ManyToOne
	@JoinColumn(name = "ID_ASSU", nullable = true)
	private AssuranceVie compteAssu;
	
	@ManyToOne
	@JoinColumn(name = "ID_OP", nullable = true)
	private Operation compteOperation;
	
	


	public Operation getCompteOperation() {
		return compteOperation;
	}

	public void setCompteOperation(Operation compteOperation) {
		this.compteOperation = compteOperation;
	}

	public LivretA getCompteLivret() {
		return compteLivret;
	}

	public void setCompteLivret(LivretA compteLivret) {
		this.compteLivret = compteLivret;
	}

	public AssuranceVie getCompteAssu() {
		return compteAssu;
	}

	public void setCompteAssu(AssuranceVie compteAssu) {
		this.compteAssu = compteAssu;
	}

	public Compte() {
		comptesClient = new HashSet<>();
	}

	public Set<Client> getComptesClient() {
		return comptesClient;
	}

	public void setComptesClient(Set<Client> comptesClient) {
		this.comptesClient = comptesClient;
	}
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSold() {
		return sold;
	}

	public void setSold(Double sold) {
		this.sold = sold;
	}
	
	
}
