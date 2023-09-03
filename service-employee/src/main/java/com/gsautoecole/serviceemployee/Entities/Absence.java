package com.gsautoecole.serviceemployee.Entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Veuillez entrer la date de debut d'absence .")
	private LocalDate dateDeb;
	@NotNull(message="Veuillez entrer la date de fin d'absence .")
	private LocalDate dateFin;
	
	private String Remarque;
	
	@NotNull(message = "Veuillez specifier un employé !")
	@ManyToOne
	@JoinColumn(name = "Employee_id",nullable = false)
	private Employee emp;
	
	@NotNull(message = "Veuillez specifier la cause d'absence !")
	@ManyToOne
	@JoinColumn(name = "causeAbs_id",nullable = false)
	private CauseAbsence causeAbsence;
	
	@JsonBackReference
	public Employee getEmp() {
		return this.emp;
	}

	@Override
	public String toString() {
		return "Absence [id=" + id + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", Remarque=" + Remarque
				+ "]";
	}

}
