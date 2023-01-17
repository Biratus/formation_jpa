package fr.ajc.jpa.live.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.User;

public class UtilisateurRepository {

	private EntityManagerFactory emf;
	
	public UtilisateurRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public Boolean create(User user) {
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
	
	public User findById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		User user = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			user = em.find(User.class,id);			
			
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

	public Boolean update(User user) {
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
			User user = em.find(User.class, id);
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

	// Requete SQL
	
	//findAll
	
	public List<User> findAllSQL() {
		EntityManager em = null;
		EntityTransaction tx = null;
		List<User> users = new ArrayList<>();
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			Query query = em.createNativeQuery("select id,username,password from Utilisateur",User.class);
			
			users = query.getResultList();
			
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

	public List<User> findAllJPQL() {
		EntityManager em = null;
		EntityTransaction tx = null;
		List<User> users = new ArrayList<>();
		try {
			
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Requètes avec le EntityManager
			TypedQuery<User> tq = em.createQuery("SELECT u FROM User u",User.class);
			
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
