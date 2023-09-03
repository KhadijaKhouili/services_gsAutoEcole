package com.gsautoecole.serviceemployee.Entities;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


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
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class AutoEcole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Ce champs est vide!")
	private String nomAuto;
	private String ville;
	
	private List<Long> categories;


	private List<Long> depenses; 


	// @JsonManagedReference
	// @OneToMany(fetch = FetchType.LAZY,mappedBy = "autoEcole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Long> employees;

	@Override
	public String toString() {
		return "AutoEcole [id=" + id + ", nomAuto=" + nomAuto + ", ville=" + ville +"]";
	}
	

	}
