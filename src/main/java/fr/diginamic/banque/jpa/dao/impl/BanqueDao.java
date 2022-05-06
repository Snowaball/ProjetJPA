package fr.diginamic.banque.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.jpa.dao.Idao;
import fr.diginamic.banque.jpa.entities.Banque;


public class BanqueDao extends Dao implements Idao<Banque> {

	public BanqueDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Banque e) throws Exception {
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
	public boolean update(Banque e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Banque ecli = em.find(Banque.class, e.getId());
			if (ecli != null) {
				ecli.setNom(e.getNom());
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
	public boolean delete(Banque e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Banque.class, e.getId());
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
    public Banque getOne(Banque e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<Banque> tqb = em.createQuery("select b from Banque b where Banque.id = :id", Banque.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<Banque> getAll() throws Exception {
        TypedQuery<Banque> tqb = fd.getEm().createQuery("select b from Banque b", Banque.class);

        return tqb.getResultList();
    }

}
