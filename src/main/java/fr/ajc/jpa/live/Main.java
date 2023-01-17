package fr.ajc.jpa.live;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		// La configuration dans persistence.xml (<persistence-unit>)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation_jpa");

		System.out.println("Connexion ok");
	}

}
