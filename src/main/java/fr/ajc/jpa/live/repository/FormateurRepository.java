package fr.ajc.jpa.live.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.Formateur;

public class FormateurRepository {

	private EntityManagerFactory emf;

	public FormateurRepository(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	public Boolean create(Formateur formateur) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean created = true;
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			em.persist(formateur);

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

	public Boolean createWithUser(Formateur formateur) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Boolean created = true;
		try {

			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			// Créer l'user
			em.persist(formateur.getUserFormateur());

			// Créer le formateur
			em.persist(formateur);

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

	public Formateur findById(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Formateur form = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			form = em.find(Formateur.class, id);

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

		return form;
	}

	public Formateur findByIdAndFetchModules(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Formateur form = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			TypedQuery<Formateur> query = em.createQuery(
					"SELECT form FROM Formateur form LEFT JOIN FETCH form.modules WHERE form.id=:id", Formateur.class);
			query.setParameter("id", id);

			form = query.getSingleResult();

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

		return form;
	}
}
