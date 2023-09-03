package com.gsautoecole.serviceemployee.Entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data @AllArgsConstructor @NoArgsConstructor
@DiscriminatorColumn(name = "TypeEmployee")
public class Employee {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Le champs nom est obligatoire !")
	private String nomEmp; 
	@NotBlank(message = "Le champs nom en arabe est obligatoire !")
	@Pattern(regexp = "^[\\p{IsArabic}]+$", message = "Le nom doit etre en arabe !")
	private String nomEmpArb;
	@NotBlank(message = "Le champs prenom est obligatoire !")
	private String prenomEmp;
	@NotBlank(message = "Le champs prenom en arabe est obligatoire !")
	@Pattern(regexp = "^[\\p{IsArabic}]+$", message = "Le prenom doit etre en arabe !")
	private String prenomEmpArb;
	@Pattern(regexp = "^[A-Z]{2}\\d{4}$", message = "Le format de CIN est incorect!")
	private String CIN;
	@Past(message = "La date de naissance doit etre une date ancienne !")
	private LocalDate dateNaissEmp;
	@NotBlank(message = "Le champs lieu de naissance est obligatoire !")
	private String lieuNaissEmp;
	@NotBlank(message = "Le champs Email est obligatoire !")
	@Email(message = "Veuillez saisir une adresse e-mail valide.")
	private String emailEmp;
	@NotBlank(message = "Le champs numero de telephone est obligatoire !")
	@Pattern(regexp = "^[0-9]{10}", message= "Numero du telephone doit contient 10 chiffres!")
	private String tel;
	@PastOrPresent(message = "La date d'embauche doit etre une date valide !")
	private LocalDate dateEmbauche;
	@NotBlank(message = "Le champs capn est obligatoire !")
	private String capnEmp;
	@NotBlank(message = "Le champs npc est obligatoire !")
	private String npcEmp;
	@NotBlank(message = "L'adresse est obligatoire !")
	private String addrEmp;
	
	//@NotBlank(message = "L'adresse est obligatoire !")
	private String etat;
//	@Enumerated(EnumType.STRING)
	
	//@NotNull(message = "Veuillez specifier le type d'emploie !")
	@ManyToOne
	@JoinColumn(name = "TEmploie_id",nullable = false)
	private TypeEmploie typeEmp; 
	
	
	private String Observations;
	
	// @ManyToOne
    // @JoinColumn(name = "auto_id",nullable = false)
    private Long autoEcoleId;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Absence> absences ;
	
	// @JsonBackReference
	// public AutoEcole getAutoEcole() {
	// 	return this.autoEcole;
	// }
	@JsonManagedReference
	public List<Absence> getAbsences(){
		return this.absences;
	}
	
	 public void addAbsence(Absence absence) {
	        absences.add(absence);
	        absence.setEmp(this);
	 }

	 public void removeAbsence(Absence absence) {
	        absences.remove(absence);
	        absence.setEmp(this);
	 }
}
