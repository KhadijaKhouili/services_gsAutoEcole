package com.gsautoecole.serviceemployee.Entities;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class CategorieMonit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Veuillez specifier la note de cette categorie !")
	private int noteReq;
	@NotNull(message = "Veuillez specifier le nombre des questions de cette categorie !")
	private int nbrQst;
	@NotNull(message = "Veuillez specifier la categorie ! ")
	@Enumerated(EnumType.STRING)
	private TypeCategorie libelle ;
	

	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private List<Moniteur> moniteurs = new ArrayList<>();
	
	
	public void addMoniteur(Moniteur m) {
		
        this.moniteurs.add(m);
        m.getCategories().add(this);
    
    }
	public void removeMoniteur(Moniteur m){
		this.moniteurs.remove(m);
	}
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", noteReq=" + noteReq + ", nbrQst=" + nbrQst + ", libelle=" + libelle
				+"]";
	}

    
}
