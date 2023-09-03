package com.gsautoecole.servicedepenses.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsautoecole.servicedepenses.enums.TypeDepense;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Veuillez spécifier le type du catégorie (Personnel,Véhicule ou Local)!")
	@Enumerated(EnumType.STRING)
	private TypeDepense typeDep;
	@NotBlank(message = "Veuillez spécifier le nom du catégorie !")
	private String categName;
	
	// @JsonManagedReference(value = "refcat")
	@JsonIgnore
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Depense> depenses = new ArrayList<>();

	private Long autoEcoleId;
	
	 public void addDepense(Depense dep) {
	        depenses.add(dep);
	        dep.setCategorie(this);
	 }

	 public void removeDepense(Depense dep) {
	        depenses.remove(dep);
	 }

	
}
