package com.gsautoecole.serviceemployee.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;
import com.gsautoecole.serviceemployee.enums.TypeMoniteur;

@RepositoryRestResource
public interface MoniteurDao extends JpaRepository<Moniteur, Long>{
	
//	public Moniteur findByIdAnd
	public Moniteur findByIdAndAutoEcoleId(Long idmonit, Long idAuto);
    

    public Moniteur findMoniteurByIdAndAutoEcoleIdAndTypeMoniteur(Long idm, Long idA, TypeMoniteur type);

    public Set<Moniteur> findByCategoriesLibelleAndAutoEcoleId(TypeCategorie libelle, Long idAuto);

    public Set<Moniteur> findByCategoriesLibelleAndTypeMoniteurAndAutoEcoleId(TypeCategorie libelle, TypeMoniteur type, Long idA);

}
