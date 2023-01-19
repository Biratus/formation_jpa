package fr.ajc.jpa.live.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="module")
@SequenceGenerator(name="Module_gen",sequenceName="module_seq",initialValue=1,allocationSize=1)
public class Module {

	@Id
	@GeneratedValue(generator="Module_gen")
	private Integer id;
	private String libelle;
	
	@Column(name="date_debut")
	private LocalDate dateDebut;
	
	@Column(name="date_fin")
	private LocalDate dateFin;
	
	@ManyToOne
	@JoinColumn(name="formateur_id")//La colonne dans la table {module} qui fait la jointure vers l'autre table {formateur}
	private Formateur formateur;
	
	
	@ManyToMany(mappedBy="modules")
	private List<Stagiaire> stagiaires;

	public Module() {
		super();
	}

	public Module(String libelle, LocalDate dateDebut, LocalDate dateFin) {
		super();
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Module(String libelle, LocalDate dateDebut, LocalDate dateFin, Formateur formateur) {
		super();
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.formateur = formateur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", libelle=" + libelle + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + "]";
	}
	
	
}
