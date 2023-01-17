package fr.ajc.jpa.live.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.ajc.jpa.live.entity.Utilisateur;

public class UtilisateurRepository {

	private EntityManagerFactory emf;
	
	public UtilisateurRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public Boolean create(Utilisateur user) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean created = true;
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			em.persist(user);
			
			
			tx.commit();			
		} catch(Exception e) {
			created = false;
			// Erreur bdd
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}		
		} finally {
			if(em!=null) {
				em.close();
			}
		}
		
		return created;
	}
	
	public Utilisateur findById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Utilisateur user = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			user = em.find(Utilisateur.class,id);			
			
			tx.commit();			
		} catch(Exception e) {
			// Erreur bdd
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}		
		} finally {
			if(em!=null) {
				em.close();
			}
		}
		
		return user;
	}

	public Boolean update(Utilisateur user) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean updated = true;
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			em.merge(user);
			
			
			tx.commit();			
		} catch(Exception e) {
			updated = false;
			// Erreur bdd
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}		
		} finally {
			if(em!=null) {
				em.close();
			}
		}
		
		return updated;
	}
	
	public Boolean delete(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean deleted = true;
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			Utilisateur user = em.find(Utilisateur.class, id);
			em.remove(user);
			
			
			tx.commit();			
		} catch(Exception e) {
			deleted = false;
			// Erreur bdd
			if(tx!=null && tx.isActive()) {
				tx.rollback();
			}		
		} finally {
			if(em!=null) {
				em.close();
			}
		}
		
		return deleted;
	}
}
