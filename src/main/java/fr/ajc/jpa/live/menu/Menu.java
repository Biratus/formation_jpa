package fr.ajc.jpa.live.menu;

import java.util.List;
import java.util.Scanner;

import fr.ajc.jpa.live.Main;
import fr.ajc.jpa.live.entity.User;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	public static void start() {
		Integer choice;
		do {
			System.out.println("1 - S'inscrire\n2 - Se connecter\n3 - Quitter");
			choice = sc.nextInt();
			if (choice == 1) {
				register();
			} else if (choice == 2) {
				login();
				choice = 3; // Quitte
			}
		} while (choice != 3);
		System.out.println("Aurevoir.");
	}

	private static void login() {
		System.out.println("-- Connexion --");
		while (true) {
			System.out.println("Votre identifiant : ");
			String identifiant = sc.next();
			System.out.println("Votre mot de passe : ");
			String mdp = sc.next();
			List<User> matchs = Main.userRepo.findByUsernameAndPassword(identifiant, mdp);
			if (matchs.isEmpty()) {
				System.out.println("Identifiant ou mot de passe incorrect.\n1 - Réessayer\n2 - S'inscrire");
				Integer choice = sc.nextInt();
				if (choice == 2)
					register();
			} else {
				User u = matchs.get(0);
				System.out.println("Connexion réussie !!!!!!\nBienvenue " + u.getUsername());

				break;
			}
		}

	}

	private static void register() {
		System.out.println("-- Inscription --");
		while (true) {
			System.out.println("Votre identifiant : ");
			String identifiant = sc.next();
			System.out.println("Votre mot de passe : ");
			String mdp = sc.next();
			if (Main.userRepo.findByUsername(identifiant).size() == 0) {
				List<User> allUsers = Main.userRepo.findAllJPQL();
				Integer id = 0;
				if (!allUsers.isEmpty())
					id = allUsers.get(allUsers.size() - 1).getId() + 1;

				Main.userRepo.create(new User(id, identifiant, mdp));
				break;
			} else {
				System.out.println("Le nom d'utilisateur " + identifiant + " existe déjà. Veuillez réessayer");
			}
		}
		System.out.println("Inscription réussie, revenez pour vous connecter !");
	}

}
