package fr.ajc.jpa.live.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.ajc.jpa.live.entity.Stagiaire;

public class StagiaireRepository {
	private EntityManagerFactory emf;

	public StagiaireRepository(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	public Boolean create(Stagiaire stag) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean created = true;
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			em.persist(stag);
			
			
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
}
