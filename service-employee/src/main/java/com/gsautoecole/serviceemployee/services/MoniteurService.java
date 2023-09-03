package com.gsautoecole.serviceemployee.services;

import java.util.List;
import java.util.Map;

import com.gsautoecole.serviceemployee.Entities.CategorieMonit;
import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;
import com.gsautoecole.serviceemployee.enums.TypeMoniteur;


public interface MoniteurService {
	
    public Moniteur addMoniteurAutoEcole(Long idAuto,Moniteur moniteur) ;
	
	public void updateMoniteurAutoEcole(Long idAuto,Long id,Moniteur moniteur);
	
//	public void addCategorieAuMonit(Moniteur m, Categorie c);
	
//	public void addCategoriesAuMonit(Moniteur m, List<Categorie> c);
	
	public List<CategorieMonit> getAllCategoriesMoniteur(Long idMonit);
	
	public void deleteCategorieMoniteur(Long idMonit,CategorieMonit c);

	public List<Moniteur> monit(Long id);

	public List<Moniteur> getAllMoniteurs();

	public Moniteur getMoniteur(Long idAuto,Long id);
    
	public Moniteur getMoniteurByType(Long idm, Long idA, TypeMoniteur type);

	public Map<Long, String> findByCategoriesLibelleAndType(TypeCategorie libelle, TypeMoniteur type,Long idA);
	
	public Map<Long, String> getMoniteursByCategoriesLibelle(TypeCategorie libelle,Long idAuto);
//	public void deleteMoniteur(Long id);
//	
//	public List<Moniteur> getAllMoniteurs();
//
//	public Moniteur getMoniteurById(Long id);
}
