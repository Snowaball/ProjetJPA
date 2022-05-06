package fr.diginamic.banque.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.jpa.dao.Idao;
import fr.diginamic.banque.jpa.entities.Client;
import fr.diginamic.banque.jpa.entities.Compte;
import fr.diginamic.banque.jpa.entities.Operation;

public class OperationDao extends Dao implements Idao<Operation> {

	public OperationDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Operation e) throws Exception {
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
	public boolean update(Operation e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Operation eop = em.find(Operation.class, e.getId());
			if (eop != null) {
				eop.setDate(e.getDate());
				eop.setMontant(e.getMontant());
				eop.setMotif(e.getMotif());
				eop.setOperationVirement(e.getOperationVirement());
				em.merge(eop);
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
	public boolean delete(Operation e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Operation.class, e.getId());
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
    public Operation getOne(Operation e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<Operation> tqb = em.createQuery("select o from Operation o where Operation.id = :id", Operation.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<Operation> getAll() throws Exception {
        TypedQuery<Operation> tqb = fd.getEm().createQuery("select o from Operation o", Operation.class);

        return tqb.getResultList();
    }
	
	public List<Compte> getComptes(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Compte> tqb = fd.getEm().createNamedQuery("Operation.getComptes", Compte.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}

}
