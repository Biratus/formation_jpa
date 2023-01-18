package fr.ajc.jpa.live.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="stagiaire")
@SequenceGenerator(name="Stagiaire_gen",sequenceName="stagiaire_seq",initialValue=1,allocationSize=1)
public class Stagiaire {

	@Id
	@GeneratedValue(generator="Stagiaire_gen")
	private Integer id;
	private String nom;
	private String prenom;
	
	@OneToOne
	@JoinColumn(name="utilisateur_id")
	private User userStagiaire;
	
	public Stagiaire() {
		super();
	}
	public Stagiaire(Integer id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Stagiaire(String nom, String prenom, User userStagiaire) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.userStagiaire = userStagiaire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public User getUserStagiaire() {
		return userStagiaire;
	}
	public void setUserStagiaire(User userStagiaire) {
		this.userStagiaire = userStagiaire;
	}
	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
