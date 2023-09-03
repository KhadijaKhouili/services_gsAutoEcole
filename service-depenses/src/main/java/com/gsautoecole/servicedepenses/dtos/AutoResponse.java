package com.gsautoecole.servicedepenses.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor @AllArgsConstructor
public class AutoResponse {

	private Long id;
	private String nomAuto;
	private String ville;
	private String adresse;
	private String tel;
    private String fax;
    private String email;
    private String logoAuto;
	private List<Long> categories;
    //private List<VehiculeResponse> vehicules;
	private List<Long> depenses; 
    private List<Long> employeesIds;
}
