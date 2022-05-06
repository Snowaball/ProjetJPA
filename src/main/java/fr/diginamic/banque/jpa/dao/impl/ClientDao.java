package fr.diginamic.banque.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.jpa.dao.Idao;
import fr.diginamic.banque.jpa.entities.Client;
import fr.diginamic.banque.jpa.entities.Compte;

public class ClientDao extends Dao implements Idao<Client> {

	public ClientDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();			
			return true;
		}
		catch(Exception ex) {
			throw new Exception (ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public boolean update(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Client ecli = em.find(Client.class, e.getId());
			if (ecli != null) {
				ecli.setNom(e.getNom());
				ecli.setPrenom(e.getPrenom());
				ecli.setAdresse(e.getAdresse());
				ecli.setDateNaissance(e.getDateNaissance());
				em.merge(ecli);
				em.getTransaction().commit();
				return true;
			}
			return false;

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			fd.close(em);
		}
	}

	@Override
	public boolean delete(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Client.class, e.getId());
			if(e != null) {
				em.remove(e);
				em.getTransaction().commit();			
				return true;
			}
			return false;
			
		}
		catch(Exception ex) {
			throw new Exception (ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
    public Client getOne(Client e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<Client> tqb = em.createQuery("select c from Client c where Client.id = :id", Client.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<Client> getAll() throws Exception {
        TypedQuery<Client> tqb = fd.getEm().createQuery("select c from Client c", Client.class);

        return tqb.getResultList();
    }
	
	public List<Compte> getComptes(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Compte> tqb = fd.getEm().createNamedQuery("Client.getComptes", Compte.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}

}
