package com.gsautoecole.servicedepenses.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gsautoecole.servicedepenses.entities.Categorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepenseResponse {
	private Long id;
	private LocalDate dateDep;
	private int montant;
	private String remarque;
	private String designation; //salaire,...
	private Categorie categorie;
	private Long AutoEcoleId;
	private VehiculeResponse vehicule;
	private EmployeeResponse emp;
	
}
