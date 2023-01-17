package fr.ajc.jpa.live;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ajc.jpa.live.entity.Utilisateur;
import fr.ajc.jpa.live.repository.UtilisateurRepository;

public class Main {

	public static void main(String[] args) {
		// La configuration dans persistence.xml (<persistence-unit>)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation_jpa");

		// Instanciation du repository
		UtilisateurRepository userRepo = new UtilisateurRepository(emf);
		
		// CREATE
//		Utilisateur user = new Utilisateur(1,"cbirette","cbirettemdp");
		
//		if(userRepo.create(user)) {
//			System.out.println("Utilisateur crée !!");
//		} else {
//			System.out.println("Problème !!");
//		}
		
		// READ (by Id)
		Utilisateur userBdd = userRepo.findById(1);
		System.out.println("Utilisateur BDD : ");
		System.out.println(userBdd);
		
		// UPDATE
		userBdd.setPassword("nouveau mdp");
		if(userRepo.update(userBdd)) {
			System.out.println("Utilisateur mis à jour !!");
		} else {
			System.out.println("Problème !!");
		}
		
		// DELETE
		
		if(userRepo.delete(userBdd.getId())) {
			System.out.println("Supprimmé !");
		} else {
			System.out.println("Problème !!");
		}
		
		System.out.println("Connexion ok");
	}

}
