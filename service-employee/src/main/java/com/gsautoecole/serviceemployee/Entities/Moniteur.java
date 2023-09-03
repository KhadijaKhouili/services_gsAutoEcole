package com.gsautoecole.serviceemployee.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import com.gsautoecole.serviceemployee.enums.TypeMoniteur;

//import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@Entity
public class Moniteur extends Employee{
	
	@NotNull(message = "Veuillez specifier un type de moniteur valide ! ")
	@Enumerated(EnumType.STRING)
	private TypeMoniteur typeMoniteur;
	@NotBlank(message = "Carte moniteur est obligatoire !")
	private String carteMoniteur;
	@NotNull(message = "Veuillez specifier la date d'experation de la carte moniteur ! ")
	@Future(message = "La date saisie montre que votre carte est expiré !")
	private LocalDate expirCarteMonit;
	@NotEmpty(message = "Veuiller specifier les categories associes a ce moniteur !")
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
    @JoinTable(name = "categorie_moniteur",joinColumns = @JoinColumn(name = "monit_id"),inverseJoinColumns = @JoinColumn(name = "categorie_id"))
	private List<CategorieMonit> categories = new ArrayList<>();
	
	public void removeCategorie(CategorieMonit c){
		this.categories.remove(c);
		c.getMoniteurs().remove(this);
	}
	
	public void addCategorie(CategorieMonit c) {
		this.categories.add(c);
		c.getMoniteurs().add(this);
	}
	
	public void setCategories(List<CategorieMonit> l){
		this.categories = l;
		for(CategorieMonit c: l) {
			c.getMoniteurs().add(this);
		}
	}

	@Override
	public String toString() {
		return "Moniteur [typeMoniteur=" + typeMoniteur + ", carteMoniteur=" + carteMoniteur + ", expirCarteMonit="
				+ expirCarteMonit + ", categories=" + categories + ", autoEcole=" + getAutoEcoleId()
				+ ", absences()=" + getAbsences() + ", id=" + getId() + ", getNomEmp()=" + getNomEmp()
				+ ", getNomEmpArb()=" + getNomEmpArb() + ", getPrenomEmp()=" + getPrenomEmp() + ", getPrenomEmpArb()="
				+ getPrenomEmpArb() + ", getCIN()=" + getCIN() + ", getDateNaissEmp()=" + getDateNaissEmp()
				+ ", getLieuNaissEmp()=" + getLieuNaissEmp() + ", getEmailEmp()=" + getEmailEmp() + ", getTel()="
				+ getTel() + ", getDateEmbauche()=" + getDateEmbauche() + ", getCapnEmp()=" + getCapnEmp()
				+ ", getNpcEmp()=" + getNpcEmp() + ", getAddrEmp()=" + getAddrEmp() + ", getEtat()=" + getEtat()
				+ ", getTypeEmp()=" + getTypeEmp() + ", getObservations()=" + getObservations() + "]";
	}
	
}
