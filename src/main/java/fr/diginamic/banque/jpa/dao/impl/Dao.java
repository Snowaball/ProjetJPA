package fr.diginamic.banque.jpa.dao.impl;


public abstract class Dao {
	
	protected final FactoryDao fd;
	
	public Dao(FactoryDao fd) {
		this.fd = fd;
	}
	

}