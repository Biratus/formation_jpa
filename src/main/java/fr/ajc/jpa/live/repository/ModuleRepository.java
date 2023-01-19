package fr.ajc.jpa.live.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.ajc.jpa.live.entity.Module;

public class ModuleRepository extends EntityRepository<Module> {

	public ModuleRepository(EntityManagerFactory emf) {
		super(emf, Module.class);
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

			query.setParameter("date", date);

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
