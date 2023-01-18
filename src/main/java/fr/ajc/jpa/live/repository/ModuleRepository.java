package fr.ajc.jpa.live.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.Module;

public class ModuleRepository {

	private EntityManagerFactory emf;

	public ModuleRepository(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	// Create
	public Boolean create(Module mod) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean created = true;
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			em.persist(mod);

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

	public Boolean update(Module mod) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean updated = true;
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			em.merge(mod);

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

	public Module findById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Module mod = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			mod = em.find(Module.class, id);

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

		return mod;
	}

	// modules de la journée
	public List<Module> findByDate(LocalDate date) {
		List<Module> mods = new ArrayList<Module>();
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			TypedQuery<Module> query = em
					.createQuery("SELECT m FROM Module m WHERE :date>=m.dateDebut and :date<=m.dateFin", Module.class);

			query.setParameter("date",date);
			
			mods = query.getResultList();

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
		return mods;
	}

}
