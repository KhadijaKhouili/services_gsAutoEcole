package com.gsautoecole.serviceemployee.services;

import java.util.List;

import com.gsautoecole.serviceemployee.Entities.CategorieMonit;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;

public interface ICategorieService {
	
	public void addCategorieAuMonit(Long idMonit,CategorieMonit c) ;
	
	public void addCategorie(CategorieMonit categorie);
		
	public void updateCategorieMonit(Long idMonit,CategorieMonit c);
	
	public List<CategorieMonit> getAllCategoriesMoniteur(Long idMonit);
	
	public void deleteCategorieMoniteur(Long idMonit,CategorieMonit c);

	public CategorieMonit getCategorieByLibelle(Long idAuto,TypeCategorie type);
	//public Categorie getAllMoniteursCategories()
}
