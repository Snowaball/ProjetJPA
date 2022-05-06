package fr.diginamic.banque.jpa.jpa;

import java.util.Date;

import fr.diginamic.banque.jpa.dao.impl.AssuranceVieDao;
import fr.diginamic.banque.jpa.dao.impl.BanqueDao;
import fr.diginamic.banque.jpa.dao.impl.ClientDao;
import fr.diginamic.banque.jpa.dao.impl.CompteDao;
import fr.diginamic.banque.jpa.dao.impl.FactoryDao;
import fr.diginamic.banque.jpa.dao.impl.LivretADao;
import fr.diginamic.banque.jpa.dao.impl.OperationDao;
import fr.diginamic.banque.jpa.dao.impl.VirementDao;
import fr.diginamic.banque.jpa.entities.Adresse;
import fr.diginamic.banque.jpa.entities.AssuranceVie;
import fr.diginamic.banque.jpa.entities.Banque;
import fr.diginamic.banque.jpa.entities.Client;
import fr.diginamic.banque.jpa.entities.Compte;
import fr.diginamic.banque.jpa.entities.LivretA;
import fr.diginamic.banque.jpa.entities.Operation;
import fr.diginamic.banque.jpa.entities.Virement;

public class AppFactory {

	public static FactoryDao BANQUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			AppFactory.BANQUE = new FactoryDao("banque");

			// Client
			ClientDao cldo = new ClientDao(AppFactory.BANQUE);

			// Assurance
			AssuranceVieDao assdo = new AssuranceVieDao(AppFactory.BANQUE);

			// Banque
			BanqueDao bdo = new BanqueDao(AppFactory.BANQUE);

			// Compte
			CompteDao comdo = new CompteDao(AppFactory.BANQUE);

			// LivretA
			LivretADao livdo = new LivretADao(AppFactory.BANQUE);

			// Operation
			OperationDao opdo = new OperationDao(AppFactory.BANQUE);

			// Virement
			VirementDao virdo = new VirementDao(AppFactory.BANQUE);

			// Création d'une Banque
			Banque b = new Banque();
			b.setNom("Banque Hamza");
			bdo.add(b);

			// Création d'un Client
			Client cl = new Client();
			cl.setNom("Amhaouch");
			cl.setPrenom("Hamza");
			cl.setDateNaissance(new Date());
			cl.setAdresse(new Adresse());
			cldo.add(cl);

			// Création d'un 2ème Client
			Client cl2 = new Client();
			cl2.setNom("Balouzet");
			cl2.setPrenom("Marc");
			cl2.setDateNaissance(new Date());
			cl2.setAdresse(new Adresse());
			cldo.add(cl2);
			//Création d'un Compte
			Compte com = new Compte();
			com.setNumero("C001");
			com.setSold(200.45);
			// Association du Compte pour 2 Clients
			com.getComptesClient().add(cl);
			com.getComptesClient().add(cl2);
			comdo.add(com);

			// Création 2ème Compte
			Compte com2 = new Compte();
			com2.setNumero("C002");
			com2.setSold(900.45);
			com2.getComptesClient().add(cl2);
			comdo.add(com2);

			// Création d'un Virement
			Virement vir = new Virement();
			vir.setBenificiaire("Hamza");
			virdo.add(vir);

			// Création d'une Operation
			Operation op = new Operation();
			op.setDate(new Date());
			op.setMontant(300.25);
			op.setMotif("Salaire entrant");
			op.setOperationVirement(vir);
			opdo.add(op);

			// Création d'une Operation
			Operation op2 = new Operation();
			op2.setDate(new Date());
			op2.setMontant(300.25);
			op2.setMotif("Salaire entrant");
			opdo.add(op2);

			// Création 3ème Compte
			Compte com3 = new Compte();
			com3.setNumero("C003");
			com3.setSold(1800.45);
			com3.getComptesClient().add(cl2);
			com3.setCompteOperation(op);
			comdo.add(com3);

			// Création 4ème Compte
			Compte com4 = new Compte();
			com4.setNumero("C003");
			com4.setSold(945.5);
			com4.getComptesClient().add(cl2);
			com4.setCompteOperation(op2);
			comdo.add(com4);

			// Création d'un Livret A
			LivretA liv = new LivretA();
			liv.setTaux(2.0);
			livdo.add(liv);
			com2.setCompteLivret(liv);
			comdo.update(com2);

			// Création d'une AssuranceVie
			AssuranceVie ass = new AssuranceVie();
			ass.setTaux(2.0);
			ass.setDateFin(new Date());
			assdo.add(ass);
			com.setCompteAssu(ass);
			comdo.update(com);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

	}

}
