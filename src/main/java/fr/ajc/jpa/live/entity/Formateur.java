package fr.ajc.jpa.live.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="formateur")
@SequenceGenerator(name="Formateur_gen",sequenceName="formateur_seq",initialValue=1,allocationSize=1)
public class Formateur {

	@Id
	@GeneratedValue(generator="Formateur_gen")
	private Integer id;
	private String nom;
	private String prenom;
	
	@OneToOne
	@JoinColumn(name="utilisateur_id") // JoinColumn => porte la relation (= celui qui a la FK dans la table)
	private User userFormateur;
	
	public Formateur() {
		super();
	}
	public Formateur(Integer id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Formateur(String nom, String prenom, User userFormateur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.userFormateur = userFormateur;
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
	
	public User getUserFormateur() {
		return userFormateur;
	}
	public void setUserFormateur(User userFormateur) {
		this.userFormateur = userFormateur;
	}
	@Override
	public String toString() {
		return "Formateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
	
}
