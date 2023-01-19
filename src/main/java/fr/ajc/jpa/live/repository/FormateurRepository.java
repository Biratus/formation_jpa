package fr.ajc.jpa.live.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.Formateur;

public class FormateurRepository extends EntityRepository<Formateur> {

	private EntityManagerFactory emf;

	public FormateurRepository(EntityManagerFactory emf) {
		super(emf, Formateur.class);
	}

	// Le formateur doit avoir un user relié (en Java)
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

	// Les formateurs qui animent des modules à une certaine date
	public List<Formateur> findWorkingAtDate(LocalDate date) {
		EntityManager em = null;
		EntityTransaction tx = null;
		List<Formateur> forms = new ArrayList<>();
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			TypedQuery<Formateur> query = em.createQuery("SELECT DISTINCT form FROM Formateur form "
					+ "LEFT JOIN FETCH form.modules m " + "WHERE :date>=m.dateDebut and :date<=m.dateFin",
					Formateur.class);
			query.setParameter("date", date);

			forms = query.getResultList();

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

		return forms;
	}
}
