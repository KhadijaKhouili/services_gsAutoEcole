package com.gsautoecole.servicedepenses.dtos;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class VehiculeResponse {

    private int id ;
    private int autoecole_id ;
    private String img_carte_grise ;
    private String img_assurance ;
    private String img_visite_tech ;
    private String img_vignette ;
    private String modele ;
    private int matricule ;
    private String fornisseur ;
    private String marque ;
    private String categorie_permis ;
    private LocalDate date_p_visete_technique ;
    private LocalDate date_vidange ;
    private LocalDate date_p_vidange ;
    private LocalDate date_assurance ;
    private LocalDate date_e_assurance ;
}
