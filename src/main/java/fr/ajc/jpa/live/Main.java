package fr.ajc.jpa.live;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ajc.jpa.live.entity.Module;
import fr.ajc.jpa.live.entity.User;
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
		
		List<Module> mods = moduleRepo.findByDate(LocalDate.of(2023, 1,28));
		System.out.println("---------------");
		System.out.println(mods);
		
//		Formateur form = formateurRepo.findById(1);
//		
////		System.out.println(form.getModules());
//
//		Module m = new Module("Spring",LocalDate.of(2023, 1, 25),LocalDate.of(2023, 2, 1),form);
//		
//		if(moduleRepo.create(m)) {
//			System.out.println("Module ok");
//			
//			form = formateurRepo.findByIdAndFetchModules(1);
//			System.out.println("---------------");
//			
//			System.out.println(form.getModules());
//			
//		} else {
//			System.out.println("module ko");
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
