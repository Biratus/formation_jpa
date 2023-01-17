package fr.ajc.jpa.live.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Doit être gérée en BDD (=> il doit y avoir une table correspondante)
@Table(name="Utilisateur")// La table en bdd
public class User {

	@Id // Clé primaire
	private Integer id;
	
	private String username;

	@Column(name="password")// La colonne en bdd
	private String pwd;
	
	public User() {
		super();
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
