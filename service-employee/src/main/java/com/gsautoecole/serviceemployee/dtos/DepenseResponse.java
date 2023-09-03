package com.gsautoecole.serviceemployee.dtos;

import java.time.LocalDate;
import java.util.List;

import com.gsautoecole.serviceemployee.Entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor @AllArgsConstructor
public class DepenseResponse {
	private Long id;
	private LocalDate dateDep;
	private int montant;
	private String remarque;
	private String designation; //salaire,...
	private CategorieResponse categorie;
	private Long idautoEcole;
	// private Employee emp;
	private Long idvehicule;
	private List<Employee> emp;
}
