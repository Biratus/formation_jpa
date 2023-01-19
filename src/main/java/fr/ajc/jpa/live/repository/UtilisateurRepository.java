package fr.ajc.jpa.live.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.User;

public class UtilisateurRepository extends EntityRepository<User> {
	
	public UtilisateurRepository(EntityManagerFactory emf) {
		super(emf,User.class);
	}
	
	// JPQL
	public List<User> findByUsername(String username) {
		EntityManager em = null;
		EntityTransaction tx = null;
		List<User> users = new ArrayList<>();
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.username=:username",User.class);
			
			tq.setParameter("username", username);
			
			users = tq.getResultList();
			
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
		
		return users;
	}

	public List<User> findByUsernameAndPassword(String identifiant, String mdp) {
		List<User> users = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// JPQL => Avec le nom des classes et des attributs JAVA !!!
			TypedQuery<User> q = em.createQuery(
					"SELECT u FROM User u WHERE u.username = :identifiant and u.pwd = :mdp", User.class);
			
			q.setParameter("identifiant", identifiant);
			q.setParameter("mdp", mdp);

			users = q.getResultList();

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return users;
	}
}
