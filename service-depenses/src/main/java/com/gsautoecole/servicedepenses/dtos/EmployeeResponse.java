package com.gsautoecole.servicedepenses.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // cela utiliser pour exclure tous les valeurs nulls dans les reponses JSON
public class EmployeeResponse {
    private Long id;
	private String nom_emp; 
	private String nom_emp_arb;
	private String prenom_emp;
	private String prenom_emp_arb;
	private String cin;
	private LocalDate date_naiss_emp;
	private String lieu_naiss_emp;
	private String email_emp;
	private String tel;
	private LocalDate date_embauche;
	private String capn_emp;
	private String npc_emp;
	private String addr_emp;
	private String etat;
	private String observations;
    private Long auto_ecole;
	private String type_moniteur;
	private String carte_moniteur;
	private LocalDate expir_carte_monit;
	//private List<CategorieMonit> categories = new ArrayList<>();
}
