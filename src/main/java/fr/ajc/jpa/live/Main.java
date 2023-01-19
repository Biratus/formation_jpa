package fr.ajc.jpa.live;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ajc.jpa.live.entity.Formateur;
import fr.ajc.jpa.live.entity.Stagiaire;
import fr.ajc.jpa.live.entity.Module;
import fr.ajc.jpa.live.entity.User;
import fr.ajc.jpa.live.repository.EntityRepository;
import fr.ajc.jpa.live.repository.FormateurRepository;
import fr.ajc.jpa.live.repository.ModuleRepository;
import fr.ajc.jpa.live.repository.StagiaireRepository;
import fr.ajc.jpa.live.repository.UtilisateurRepository;


public class Main {

	public static UtilisateurRepository userRepo;
	public static FormateurRepository formateurRepo;
	public static StagiaireRepository stagiaireRepo;
	public static ModuleRepository moduleRepo;

	public static void main(String[] args) {
		// La configuration dans persistence.xml (<persistence-unit>)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation_jpa");

		initRepos(emf);
		
		// Connexion Stagiaire-Module
		// Affecter un module à un stagiaire
		Stagiaire s = stagiaireRepo.findByIdAndFetchModules(3);
		Module m = moduleRepo.findById(1);
		
		s.getModules().add(m);
		stagiaireRepo.update(s);

		
		
		
		
		// Read with ManyToMany Stagiaire
//		Stagiaire s = stagiaireRepo.findByIdAndFetchModules(1);
//		System.out.println(s);
//		System.out.println("------------");
////		System.out.println(s.getModules());
//		for(Module m : s.getModules()) {
//			Formateur f = m.getFormateur();
//			System.out.println(m.getLibelle()+" : "+f);
//		}
		
//		Menu.start();

		System.out.println("Connexion ok");
	}

	public static void initRepos(EntityManagerFactory emf) {
		// Instanciation du repository
		userRepo = new UtilisateurRepository(emf);
		formateurRepo = new FormateurRepository(emf);
		stagiaireRepo = new StagiaireRepository(emf);
		moduleRepo = new ModuleRepository(emf);
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
