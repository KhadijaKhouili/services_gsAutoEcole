package com.gsautoecole.serviceemployee.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gsautoecole.serviceemployee.Entities.CategorieMonit;
import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.Exceptions.EmployeeAlreadyExistsException;
import com.gsautoecole.serviceemployee.dao.CategorieDao;
import com.gsautoecole.serviceemployee.dao.MoniteurDao;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Transactional
@Service
public class CategorieServiceImp implements ICategorieService{
	
	private CategorieDao catDao;
	private MoniteurDao monitDao;
	
	public CategorieServiceImp(CategorieDao c,MoniteurDao monitDao) {
		this.catDao = c;
		this.monitDao = monitDao;
	}

	@Override
	public void addCategorieAuMonit(Long idMonit, CategorieMonit c) {
		Moniteur m = monitDao.findById(idMonit).orElse(null);
		List<CategorieMonit> categories = m.getCategories();
        boolean ExistCat = false;
        if(categories.size() > 0) {
			for(CategorieMonit cat : categories) {
				if(cat.getLibelle().equals(c.getLibelle())) {
					ExistCat=true;
				}
			}
        }
		if(!ExistCat) {
			m.addCategorie(c);
			m.setId(idMonit);
			monitDao.save(m);
		}else {
			throw new EmployeeAlreadyExistsException("Cette catégorie est déjà associées à ce moniteur !");
		}
		
	}

	@Override
	public void updateCategorieMonit(Long idMonit, CategorieMonit c) {
		
		
	}

	@Override
	public List<CategorieMonit> getAllCategoriesMoniteur(Long idMonit) {
		// TODO Auto-generated method stub
		return monitDao.findById(idMonit).get().getCategories();
	}

	@Override
	public void deleteCategorieMoniteur(Long idMonit,CategorieMonit c) {
		Moniteur m = monitDao.findById(idMonit).orElse(null);
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
			monitDao.save(m);
		}else {
			throw new EntityNotFoundException("Cette catégorie n'existe pas dans la liste des catégories associées à ce moniteur !");
		}
		
		
	}

	@Override
	public void addCategorie(CategorieMonit categorie) {
		catDao.save(categorie);
		
	}

	@Override
	public CategorieMonit getCategorieByLibelle(Long idAuto,TypeCategorie type){
		CategorieMonit categorie = catDao.findByMoniteursAutoEcoleIdAndLibelle(idAuto,type);
		if(categorie != null){
			return categorie;
		}else{
			throw new EntityNotFoundException("Cette catégorie n'existe pas dans cette auto !");
		}
	}

}
