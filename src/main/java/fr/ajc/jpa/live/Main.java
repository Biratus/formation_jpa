package fr.ajc.jpa.live;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ajc.jpa.live.entity.User;
import fr.ajc.jpa.live.repository.UtilisateurRepository;

public class Main {

	static UtilisateurRepository userRepo;

	public static void main(String[] args) {
		// La configuration dans persistence.xml (<persistence-unit>)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation_jpa");

		// Instanciation du repository
		userRepo = new UtilisateurRepository(emf);

	
		// Tests CRUD
//		 create();
		// read();
		// update();
		// delete();

		System.out.println(userRepo.findByUsername("invite"));
		

		System.out.println("Connexion ok");
	}

	public static void create() {
		// CREATE
		User user = new User(2, "invite", "invitemdp");

		if (userRepo.create(user)) {
			System.out.println("Utilisateur crée !!");
		} else {
			System.out.println("Problème !!");
		}
	}

	public static void read() {
		// READ (by Id)
		User userBdd = userRepo.findById(1);
		System.out.println("Utilisateur BDD : ");
		System.out.println(userBdd);

	}

	public static void update() {
		// UPDATE
		User userBdd = userRepo.findById(1);
		userBdd.setPassword("nouveau mdp");
		if (userRepo.update(userBdd)) {
			System.out.println("Utilisateur mis à jour !!");
		} else {
			System.out.println("Problème !!");
		}
	}

	public static void delete() {
		// DELETE
		if (userRepo.delete(1)) {
			System.out.println("Supprimmé !");
		} else {
			System.out.println("Problème !!");
		}
	}

}
