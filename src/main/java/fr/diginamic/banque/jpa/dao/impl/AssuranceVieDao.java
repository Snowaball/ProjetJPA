package fr.diginamic.banque.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.jpa.dao.Idao;
import fr.diginamic.banque.jpa.entities.AssuranceVie;
import fr.diginamic.banque.jpa.entities.Client;
import fr.diginamic.banque.jpa.entities.Compte;


public class AssuranceVieDao extends Dao implements Idao<AssuranceVie> {

	public AssuranceVieDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(AssuranceVie e) throws Exception {
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
	public boolean update(AssuranceVie e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			AssuranceVie ecli = em.find(AssuranceVie.class, e.getId());
			if (ecli != null) {
				ecli.setTaux(e.getTaux());
				ecli.setDateFin(e.getDateFin());
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
	public boolean delete(AssuranceVie e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(AssuranceVie.class, e.getId());
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
    public AssuranceVie getOne(AssuranceVie e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<AssuranceVie> tqb = em.createQuery("select a from AssuranceVie a where AssuranceVie.id = :id", AssuranceVie.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<AssuranceVie> getAll() throws Exception {
        TypedQuery<AssuranceVie> tqb = fd.getEm().createQuery("select a from AssuranceVie a", AssuranceVie.class);

        return tqb.getResultList();
    }
	
	public List<Compte> getComptes(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Compte> tqb = fd.getEm().createNamedQuery("AssuranceVie.getComptes", Compte.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}

}
