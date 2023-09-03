package com.gsautoecole.serviceemployee.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsautoecole.serviceemployee.Entities.CategorieMonit;
import com.gsautoecole.serviceemployee.Entities.Employee;
import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.Exceptions.EmployeeAlreadyExistsException;
import com.gsautoecole.serviceemployee.dao.CategorieDao;
import com.gsautoecole.serviceemployee.dao.EmployeeDao;
import com.gsautoecole.serviceemployee.dao.MoniteurDao;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;
import com.gsautoecole.serviceemployee.enums.TypeMoniteur;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MoniteurServiceImp implements MoniteurService{
	
	private MoniteurDao moniteurDao;
	private CategorieDao catDao;
	private EmployeeDao empDao;
	
	public MoniteurServiceImp(MoniteurDao dao,CategorieDao catDao,EmployeeDao empDao) {
		this.moniteurDao=dao;
	    this.catDao = catDao;
		this.empDao = empDao;
	}
    
	// recuperer les moniteurs par categories
	@Override
	public List<Moniteur> monit(Long idcat) {
		return catDao.findById(idcat).get().getMoniteurs();
	}

	// ajouter un moniteur a une auto ecole
	@Override
	public Moniteur addMoniteurAutoEcole(Long idAuto,Moniteur moniteur) {
		if (empDao.findByCINAndAutoEcoleId(moniteur.getCIN(),idAuto) != null) {
			throw new EmployeeAlreadyExistsException(" L'employé avec CIN "+moniteur.getCIN()+" existe déjà dans cette auto-école !");
		}
		// Vérifiez si le type de l'employé est moniteur
		if(! (moniteur.getTypeEmp().getTpemploie()).equals("Moniteur")) {
			throw new EmployeeAlreadyExistsException("Type d'emploie doit etre 'Moniteur' !");
		}
		// Ajouter les informations de l'auto-ecole dans les infos de moniteur a ajoutee
		moniteur.setAutoEcoleId(idAuto);
		moniteur.setEtat("ACTIVE");
		return moniteurDao.save(moniteur);
	}
	
	// modifier un moniteur 
	@Override
	public void updateMoniteurAutoEcole(Long idAuto, Long id, Moniteur moniteur) {
		Employee mAncien = moniteurDao.findByIdAndAutoEcoleId(id, idAuto);
		if(mAncien != null) {
		// Vérifiez si le type de l'employé est moniteur
	      if(! (moniteur.getTypeEmp().getTpemploie()).equals("Moniteur")) {
	      	throw new EmployeeAlreadyExistsException("Type d'emploie doit etre 'Moniteur' !");
	      }
	   // Ajouter les informations de l'auto-ecole dans les infos de moniteur a ajoutee
		  moniteur.setAutoEcoleId(idAuto);
		  moniteur.setId(id);
		  moniteur.setEtat("ACTIVE");
		  List<CategorieMonit> categories = moniteur.getCategories();
		  List<CategorieMonit> Nvcategories = new ArrayList<>();
			for(CategorieMonit c : categories) {
				Nvcategories.add(catDao.findById(c.getId()).get());
			}
			moniteur.getCategories().clear();
			moniteur.getCategories().addAll(Nvcategories);
			
		  moniteurDao.save(moniteur);
		}
	}

	// Retourne tous les categories associees a un moniteur
	@Override
	public List<CategorieMonit> getAllCategoriesMoniteur(Long idMonit) {
		Moniteur moniteur = moniteurDao.findById(idMonit).get();
		if(moniteur != null){
			return moniteurDao.findById(idMonit).get().getCategories();
		}else{
			throw new EntityNotFoundException("Moniteur n'existe pas !");
		}
	}
    
	//Supprimer une categorie associee a un moniteur
	@Override
	public void deleteCategorieMoniteur(Long idMonit,CategorieMonit c) {
		Moniteur m = moniteurDao.findById(idMonit).orElse(null);
		List<CategorieMonit> categories = m.getCategories();
        boolean ExistCat = false;
        if(categories.size() > 0) {
			for(CategorieMonit cat : categories) {
				if(cat.getLibelle().equals(c.getLibelle())) {
					ExistCat=true;
				}
			}
        }
		if(ExistCat) {
			m.removeCategorie(c);
			m.setId(idMonit);
			moniteurDao.save(m);
		}else {
			throw new EntityNotFoundException("Cette catégorie n'existe pas dans la liste des catégories associées à ce moniteur !");
		}
		
		
	}
	@Override
	@Transactional
	public List<Moniteur> getAllMoniteurs(){
		List<Moniteur> moniteurs = moniteurDao.findAll();
	    return moniteurs;
	}

	@Override
	public Moniteur getMoniteur(Long idAuto,Long id){
		Moniteur moniteur = moniteurDao.findByIdAndAutoEcoleId(id, idAuto);
		if(moniteur != null){
			return moniteur;
		}else{
			throw new EntityNotFoundException("Ce moniteur n'existe pas !");
		}
		
	}
    
	// recuperer un moniteur par son type( Pratique, Theorique)
	@Override
	public Moniteur getMoniteurByType(Long idm, Long idA, TypeMoniteur type) {
		return moniteurDao.findMoniteurByIdAndAutoEcoleIdAndTypeMoniteur(idm, idA, type);
	}

	// recuperer un moniteur par son type( Pratique, Theorique) et categorie( A, B, C, ..) et idAuto
	@Override
	public Map<Long, String> getMoniteursByCategoriesLibelle(TypeCategorie libelle,Long idAuto) {
        Set<Moniteur> moniteurs = moniteurDao.findByCategoriesLibelleAndAutoEcoleId(libelle,idAuto);
        Map<Long, String> moniteurMap = new HashMap<>();

        for (Moniteur moniteur : moniteurs) {
            moniteurMap.put(moniteur.getId(), moniteur.getNomEmp());
        }

        return moniteurMap;
    }

	// recuperer un moniteur par son type( Pratique, Theorique) et categorie( A, B, C, ..) et idAuto
	@Override
	    public Map<Long, String> findByCategoriesLibelleAndType(TypeCategorie libelle, TypeMoniteur type,Long idA) {
	         Set<Moniteur> moniteurs = moniteurDao.findByCategoriesLibelleAndTypeMoniteurAndAutoEcoleId(libelle, type,idA);
	         Map<Long, String> moniteurNames = new HashMap<>();
	         for (Moniteur moniteur : moniteurs) {
	        	 moniteurNames.put(moniteur.getId(), moniteur.getNomEmp()); // Supposons que vous ayez une méthode getNom() dans la classe Employe pour récupérer le nom du moniteur
	         }
	         return moniteurNames;
	    }
}
