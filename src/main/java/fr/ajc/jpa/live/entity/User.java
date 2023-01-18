package fr.ajc.jpa.live.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // Doit être gérée en BDD (=> il doit y avoir une table correspondante)
@Table(name="Utilisateur")// La table en bdd
@SequenceGenerator(name="Utilisateur_gen",sequenceName="Utilisateur_seq",initialValue=100,allocationSize=1) // Auto increment
public class User {

	@Id // Clé primaire
	@GeneratedValue(generator="Utilisateur_gen") // Auto increment
	private Integer id;
	
	private String username;

	@Column(name="password")// La colonne en bdd
	private String pwd;
	
	// mappedBy => Quel est l'attribut (JAVA) de la classe cible (Formateur) qui contient la classe dans laquelle on est (User)
	// mappedBy => Quel est l'attribut de type "User" dans la classe Formateur
	@OneToOne(mappedBy="userFormateur")
	private Formateur formateur;

	// mappedBy => Quel est l'attribut de type "User" dans la classe Stagiaire
	@OneToOne(mappedBy="userStagiaire")
	private Stagiaire stagiaire;
	
	public User() {
		super();
	}
	
	public User(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}

	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + pwd + "]";
	}
	
	
}
