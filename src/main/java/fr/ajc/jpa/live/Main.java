package fr.ajc.jpa.live;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ajc.jpa.live.entity.Formateur;
import fr.ajc.jpa.live.entity.User;
import fr.ajc.jpa.live.repository.FormateurRepository;
import fr.ajc.jpa.live.repository.StagiaireRepository;
import fr.ajc.jpa.live.repository.UtilisateurRepository;

public class Main {

	public static UtilisateurRepository userRepo;
	public static FormateurRepository formateurRepo;
	public static StagiaireRepository stagiaireRepo;

	public static void main(String[] args) {
		// La configuration dans persistence.xml (<persistence-unit>)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation_jpa");

		// Instanciation du repository
		userRepo = new UtilisateurRepository(emf);
		formateurRepo = new FormateurRepository(emf);
		stagiaireRepo = new StagiaireRepository(emf);

		Formateur f = formateurRepo.findById(1);
		
		System.out.println(f);
		
		System.out.println("----------");
		System.out.println(f.getUserFormateur());
		
		
//		User user = new User("formateur","formateurmdp");
//		Formateur f = new Formateur("nomFormateur","prenomForm",user);
//		user.setFormateur(f);
//		
//		
//		if(formateurRepo.createWithUser(f)) {
//			System.out.println("Formateur crée !");
//		} else {
//			System.out.println("Problème lors de la création du formateur " + f);
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		// Test creation du formateur
//		User u = userRepo.findById(2);
		// user est detached
		
		
//		Stagiaire s = new Stagiaire("Dupont","Michel",u);
//		
//		if(stagiaireRepo.create(s)) {
//			System.out.println("Stagiaire crée !");
//		} else {
//			System.out.println("Problème lors de la création du stagiaire "+s);
//		}

//		Formateur form = new Formateur("Birette","Clément",u);
//		
//		if(formateurRepo.create(form)) {
//			System.out.println("Formateur crée !");
//		} else {
//			System.out.println("Problème lors de la création du formateur "+form);
//		}

		// Tests CRUD
//		 create();
		// read();
		// update();
		// delete();

//		System.out.println(userRepo.findByUsernameAndPassword("sedgujhfk","invitemdp"));
//		Menu.start();

		System.out.println("Connexion ok");
	}

	public static void create() {
		// CREATE
		User user = new User("newUser", "newUsermdp");

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
		userBdd.setPwd("nouveau mdp");
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
