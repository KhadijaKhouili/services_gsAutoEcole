package com.gsautoecole.servicedepenses.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class DepenseLocalInfoDTO {
	
	private LocalDate dateDep;
	private int montant;
    private String categorie;
    private int total;
    private String AutoName;
    private String tel;
    private String fax;
    private String email;
    private String logoAuto;
    private String adresse;
}
