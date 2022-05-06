package fr.diginamic.banque.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.banque.jpa.dao.Idao;
import fr.diginamic.banque.jpa.entities.Compte;
import fr.diginamic.banque.jpa.entities.Operation;
import fr.diginamic.banque.jpa.entities.Virement;

public class VirementDao extends Dao implements Idao<Virement> {

	public VirementDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Virement e) throws Exception {
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
	public boolean update(Virement e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Virement ecli = em.find(Virement.class, e.getId());
			if (ecli != null) {
				ecli.setBenificiaire(e.getBenificiaire());
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
	public boolean delete(Virement e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Virement.class, e.getId());
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
    public Virement getOne(Virement e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<Virement> tqb = em.createQuery("select v from Virement v where Virement.id = :id", Virement.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<Virement> getAll() throws Exception {
        TypedQuery<Virement> tqb = fd.getEm().createQuery("select v from Virement v", Virement.class);

        return tqb.getResultList();
    }
	
	public List<Operation> getComptes(Compte e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Operation> tqb = fd.getEm().createNamedQuery("Virement.getComptes", Operation.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}

}
