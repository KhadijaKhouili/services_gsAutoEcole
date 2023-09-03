package com.gsautoecole.servicedepenses.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // cela utiliser pour exclure tous les valeurs nulls dans les reponses JSON
public class Depense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PastOrPresent(message = "Veuillez spécifier une date valide !")
	private LocalDate dateDep;
	@NotNull(message = "Veuillez saisie le montant de cette dépense !")
	private int montant;
	private String remarque;
	private String designation; //salaire,...
	@ManyToOne
	@JoinColumn(name = "cat_dep_id",nullable = false)
	private Categorie categorie;

	private Long autoEcoleId;

	private Long empId;
	
	private Long vehiculeId;
	

}
