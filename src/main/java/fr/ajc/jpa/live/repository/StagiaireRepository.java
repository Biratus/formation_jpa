package fr.ajc.jpa.live.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.Formateur;
import fr.ajc.jpa.live.entity.Stagiaire;

public class StagiaireRepository extends EntityRepository<Stagiaire> {

	public StagiaireRepository(EntityManagerFactory emf) {
		super(emf, Stagiaire.class);
	}

	public Stagiaire findByIdAndFetchModules(Integer id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		Stagiaire stag = null;
		try {
			// Créer un EntityManager
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// Requètes avec le EntityManager
			TypedQuery<Stagiaire> query = em.createQuery(
					"SELECT stag FROM Stagiaire stag LEFT JOIN FETCH stag.modules WHERE stag.id=:id", Stagiaire.class);
			
			query.setParameter("id", id);

			stag = query.getSingleResult();

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

		return stag;
	}
	
}
