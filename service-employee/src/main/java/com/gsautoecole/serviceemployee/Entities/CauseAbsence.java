package com.gsautoecole.serviceemployee.Entities;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class CauseAbsence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Veuillez specifier la cause d'absence !")
	private String cause;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "causeAbsence", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Absence> absences = new ArrayList<>();
    
}
