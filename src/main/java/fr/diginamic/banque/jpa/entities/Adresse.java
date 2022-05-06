package fr.diginamic.banque.jpa.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	
	private int numero;
	private String rue;
	private int codePostal;
	private String ville;
	
	
}
