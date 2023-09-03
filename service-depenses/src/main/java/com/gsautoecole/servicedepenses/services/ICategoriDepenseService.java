package com.gsautoecole.servicedepenses.services;

import java.util.List;

import com.gsautoecole.servicedepenses.entities.Categorie;
import com.gsautoecole.servicedepenses.enums.TypeDepense;

public interface ICategoriDepenseService {
	
	public void addCategorieDepAutoEcole(Long idAuto,String typeCat,Categorie categorie);
	
	public List<Categorie> getAllCategoriesDepByAuto(Long idAuto);
	
	public Categorie updateCategorieAuto(Long idAuto,Long idCat,Categorie cat);

	public List<Categorie> getCategoriesByGroupOfids(Long idAuto,List<Long> ids);

	public List<Categorie> getAllCategoriesDep(Long idAuto,TypeDepense type);

}
