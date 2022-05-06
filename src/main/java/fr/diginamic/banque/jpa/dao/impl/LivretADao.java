package fr.diginamic.banque.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.jpa.dao.Idao;
import fr.diginamic.banque.jpa.entities.Client;
import fr.diginamic.banque.jpa.entities.Compte;
import fr.diginamic.banque.jpa.entities.LivretA;

public class LivretADao extends Dao implements Idao<LivretA> {

	public LivretADao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(LivretA e) throws Exception {
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
	public boolean update(LivretA e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			LivretA ecli = em.find(LivretA.class, e.getId());
			if (ecli != null) {
				ecli.setTaux(e.getTaux());
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
	public boolean delete(LivretA e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(LivretA.class, e.getId());
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
    public LivretA getOne(LivretA e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<LivretA> tqb = em.createQuery("select l from LivretA l where LivretA.id = :id", LivretA.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<LivretA> getAll() throws Exception {
        TypedQuery<LivretA> tqb = fd.getEm().createQuery("select l from LivretA l", LivretA.class);

        return tqb.getResultList();
    }
	
	public List<Compte> getComptes(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Compte> tqb = fd.getEm().createNamedQuery("LivretA.getComptes", Compte.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}
}
