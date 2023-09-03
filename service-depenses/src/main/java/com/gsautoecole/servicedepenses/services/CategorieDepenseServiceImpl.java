package com.gsautoecole.servicedepenses.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gsautoecole.servicedepenses.dao.CategorieDepenseDao;
import com.gsautoecole.servicedepenses.dao.IDepenseDao;
import com.gsautoecole.servicedepenses.dtos.AutoResponse;
import com.gsautoecole.servicedepenses.dtos.EmployeeResponse;
import com.gsautoecole.servicedepenses.dtos.VehiculeResponse;
import com.gsautoecole.servicedepenses.entities.Categorie;
import com.gsautoecole.servicedepenses.entities.Depense;
import com.gsautoecole.servicedepenses.enums.TypeDepense;
import com.gsautoecole.servicedepenses.exceptions.DataAlreadyExistsException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategorieDepenseServiceImpl implements ICategoriDepenseService{
    
	@Value("${serviceEmp}")
	private String serviceEmp;
	@Value("${serviceVehicule}")
	private String serviceVehicule;
	private CategorieDepenseDao catDpDao;
	private IDepenseDao depDao;
    @Autowired
	private RestTemplate restTemplate;
	
	public CategorieDepenseServiceImpl(CategorieDepenseDao catDpDao,IDepenseDao depDao) {
		this.catDpDao = catDpDao;
		this.depDao = depDao;
	}

	@Override
	public void addCategorieDepAutoEcole(Long idAuto, String typeCat, Categorie categorie) {
		System.out.println("http://"+serviceEmp+":8088/AutoEcoles/{id}");
		AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
		System.out.println("add");
		boolean exist = false;
		List<Categorie> categories = catDpDao.findByAutoEcoleId(idAuto);

		for(Categorie c:categories) {
			if(c.getTypeDep().toString().equals(categorie.getTypeDep().toString()) && c.getCategName().equals(categorie.getCategName())) {
				exist = true;
				System.out.println(c.getTypeDep().toString()+" "+categorie.getTypeDep().toString());
				System.out.println(c.getCategName()+" "+categorie.getCategName());
			}
		}
		if(auto != null ) {
			if(exist==false) {
				categorie.setAutoEcoleId(idAuto);
		    	catDpDao.save(categorie);
			}else{
				throw new DataAlreadyExistsException("Cette categorie existe deja !");
			}
		    	
		}
	}

	@Override
	public List<Categorie> getAllCategoriesDepByAuto(Long idAuto) {
		AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
		if(auto != null) {
			return catDpDao.findByAutoEcoleId(idAuto) ;
		}else {
			throw new EntityNotFoundException("L'auto-école n'a pas été trouvée !");
		}
	}
    
	// @Override
	// public List<Categorie> getAllCategoriesDepPersonnelByAuto(Long idAuto) {
	// 	AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
	// 	if(auto != null) {
	// 		//List<Categorie> categories = getCategoriesByGroupOfids(idAuto, auto.getCategories());
    //         List<Categorie> categories = catDpDao.findByAutoEcoleId(idAuto);
    //         // Filtrer les catégories ayant : typeDepense=="Personnel"
    //         List<Categorie> categoriesPersonnel = categories.stream()
    //             .filter(categorie -> categorie.getTypeDep() == TypeDepense.Personnel)
    //             .collect(Collectors.toList());
			
			
	// 		return categoriesPersonnel;
	// 	}else {
	// 		throw new EntityNotFoundException("L'auto-école n'a pas été trouvée !");
	// 	}
	// }
	@Override
    public List<Categorie> getAllCategoriesDep(Long idAuto,TypeDepense type){
		AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
		if(auto != null) {
			List<Categorie> Allcategories = catDpDao.findByAutoEcoleId(idAuto);
			if(type == TypeDepense.Personnel){
				// Filtrer les catégories ayant : typeDepense=="Personnel"
				List<Categorie> categories = Allcategories.stream()
					.filter(categorie -> categorie.getTypeDep() == TypeDepense.Personnel)
					.collect(Collectors.toList());
				return categories;
			}else if(type == TypeDepense.Local){
				// Filtrer les catégories ayant : typeDepense=="Personnel"
				List<Categorie> categories = Allcategories.stream()
					.filter(categorie -> categorie.getTypeDep() == TypeDepense.Local)
					.collect(Collectors.toList());
				return categories;
			}else if(type == TypeDepense.Véhicule){
				// Filtrer les catégories ayant : typeDepense=="Personnel"
				List<Categorie> categories = Allcategories.stream()
					.filter(categorie -> categorie.getTypeDep() == TypeDepense.Véhicule)
					.collect(Collectors.toList());
				return categories;
			}else {
			throw new EntityNotFoundException("Ce type n'a pas été trouvée !");
		}
            
		}else {
			throw new EntityNotFoundException("L'auto-école n'a pas été trouvée !");
		}
	}

	//@Override
	// public List<Categorie> getAllCategoriesDepLocalByAuto(Long idAuto) {
	// 	AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
	// 	if(auto != null) {
	// 		//List<Categorie> categories = getCategoriesByGroupOfids(idAuto, auto.getCategories());
    //         List<Categorie> categories = catDpDao.findByAutoEcoleId(idAuto);
    //         // Filtrer les catégories ayant : typeDepense=="Personnel"
    //         List<Categorie> categoriesLocal = categories.stream()
    //             .filter(categorie -> categorie.getTypeDep() == TypeDepense.Local)
    //             .collect(Collectors.toList());
	// 		return categoriesLocal;
	// 	}else {
	// 		throw new EntityNotFoundException("L'auto-école n'a pas été trouvée !");
	// 	}
	// }
	
	//@Override
	// public List<Categorie> getAllCategoriesDepVehiculeByAuto(Long idAuto) {
	// 	AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
	// 	if(auto != null) {
	// 		//List<Categorie> categories = getCategoriesByGroupOfids(idAuto, auto.getCategories());
    //         List<Categorie> categories = catDpDao.findByAutoEcoleId(idAuto);
    //         // Filtrer les catégories ayant : typeDepense=="Personnel"
    //         List<Categorie> categoriesVehicule = categories.stream()
    //             .filter(categorie -> categorie.getTypeDep() == TypeDepense.Véhicule)
    //             .collect(Collectors.toList());
	// 		return categoriesVehicule;
	// 	}else {
	// 		throw new EntityNotFoundException("L'auto-école n'a pas été trouvée !");
	// 	}
	// }
	
	@Override
	public Categorie updateCategorieAuto(Long idAuto, Long idCat, Categorie cat) {
		AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
		Categorie categorie = catDpDao.findById(idCat).orElse(null);
		// List<Depense> depenses = getGroupOfDepenses(idAuto, auto.getDepenses());
		List<Depense> depenses = depDao.findByAutoEcoleId(idAuto);
		System.out.println("auto : "+auto);
		
			if(auto != null && catDpDao.findByAutoEcoleId(idAuto).contains(categorie)) {
				System.out.println("test 1");
				System.out.println(depenses.isEmpty());
			    if(!depenses.isEmpty()) {
			    	depenses.forEach(depense->{
			    		if(depense.getCategorie().getTypeDep().name().equals(categorie.getTypeDep().name()) && depense.getCategorie().getCategName().equals(categorie.getCategName())) {
			    			
							System.out.println("type : "+depense.getCategorie().getTypeDep().name()+" ... "+categorie.getTypeDep().name());
			    			System.out.println("categ_name : "+depense.getCategorie().getCategName()+" ... "+categorie.getCategName());
			    			if(cat.getTypeDep().name().equals(TypeDepense.Personnel.name())) {
								Map<String, Long> uriVariables = new HashMap<>();
			    				uriVariables.put("idAuto", idAuto);
			    				uriVariables.put("id", depense.getEmpId());
								EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
					    		depense.setDesignation(emp.getNom_emp()+" "+emp.getPrenom_emp()+"("+cat.getCategName()+")");
					    	}else if(cat.getTypeDep().name().equals(TypeDepense.Véhicule.name())) {
								VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, depense.getVehiculeId());
					    		depense.setDesignation(vehicule.getMatricule()+":"+vehicule.getMarque());
					    	}else if(cat.getTypeDep().name().equals(TypeDepense.Local.name())) {
					    		depense.setDesignation(cat.getCategName());
					    	}
			    			
			    		}
			    		depense.setId(depense.getId());
			    		depDao.save(depense);
			    	});
			    	// categorie.setCategName(cat.getCategName());
			    }
				categorie.setCategName(cat.getCategName());
				
				return catDpDao.save(categorie);
			}else {
				throw new EntityNotFoundException("Cette categorie n'a pas été trouvé pour cette auto !");
			}
		
	}

	@Override
	public List<Categorie> getCategoriesByGroupOfids(Long idAuto, List<Long> ids) {
		List<Categorie > categories = new ArrayList<>();
		ids.forEach(id -> {
			Categorie cat = catDpDao.findById(id).get();
			if(cat != null && cat.getAutoEcoleId()==idAuto) {
				categories.add(cat);
			}	
		});
		if(categories.size() == ids.size()) {
			return categories;
		}else {
			throw new EntityNotFoundException("Les categories n'a pas été tous trouvé !");
		}
	}
	// public List<Depense> getGroupOfDepenses(Long idAuto, List<Long> ids) {
	// 	List<Depense> depenses = new ArrayList<>();
	// 	ids.forEach(id -> {
	// 		Depense cause = depDao.findByAutoEcoleIdAndId(idAuto, id);
	// 		if(cause != null) {
	// 			depenses.add(cause);
	// 		}	
	// 	});
	// 	if(depenses.size() == ids.size()) {
	// 		return depenses;
	// 	}else {
	// 		throw new EntityNotFoundException("Les dépenses n'a pas été tous trouvé !");
	// 	}

	// }

}
	
	

