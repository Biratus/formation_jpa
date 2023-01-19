package fr.ajc.jpa.live.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class EntityRepository<T> {
	
	protected EntityManagerFactory emf;
	private Class<T> entityClass;// La classe qu'on utilise dans les TypedQuery ou find etc... (Utilisateur.class, Formateur.class, etc..)

	public EntityRepository(EntityManagerFactory emf, Class<T> entityClass) {
		this.emf = emf;
		this.entityClass = entityClass;
	}

	public Boolean create(T entity) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean created = true;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			// Requètes avec le EntityManager
			em.persist(entity);
			tx.commit();
		} catch (Exception e) {
			created = false;
			// Erreur bdd
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return created;
	}

	// Object id car on ne sait pas quel sera le type de la clé primaire d'un repository, Integer, String ou autre... 
	// On pourrait parametrer ce type avec de la généricité et ajouter un type generique dans cette classe: EntityRepository<T,ID> T pour le type de donnée et ID pour le type de la clé primaire 
	public T findById(Object id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		T entity = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			// Requètes avec le EntityManager
			entity = em.find(entityClass, id);
			tx.commit();
		} catch (Exception e) {
			// Erreur bdd
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return entity;
	}

	public Boolean update(T entity) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean updated = true;
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			// Requètes avec le EntityManager
			em.merge(entity);
			tx.commit();
		} catch (Exception e) {
			updated = false;
			// Erreur bdd
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return updated;
	}

	public Boolean delete(Object id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean deleted = true;
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			// Requètes avec le EntityManager
			T entity = em.find(entityClass, id);
			em.remove(entity);
			tx.commit();
		} catch (Exception e) {
			deleted = false;
			// Erreur bdd
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return deleted;
	}

	public List<T> findAll() {
		EntityManager em = null;
		EntityTransaction tx = null;
		List<T> entities = new ArrayList<>();
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			TypedQuery<T> tq = em.createQuery("SELECT u FROM " + entityClass.getName() + " u", entityClass);

			entities = tq.getResultList();

			tx.commit();
		} catch (Exception e) {
			// Erreur bdd
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return entities;
	}
}
