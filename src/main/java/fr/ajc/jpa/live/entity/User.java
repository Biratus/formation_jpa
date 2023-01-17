package fr.ajc.jpa.live.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	public String getPassword() {
		return pwd;
	}
	public void setPassword(String password) {
		this.pwd = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + pwd + "]";
	}
	
	
}
