package com.gsautoecole.servicedepenses.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gsautoecole.servicedepenses.dao.CategorieDepenseDao;
import com.gsautoecole.servicedepenses.dao.IDepenseDao;
import com.gsautoecole.servicedepenses.dtos.AutoResponse;
import com.gsautoecole.servicedepenses.dtos.DepenseDTO;
import com.gsautoecole.servicedepenses.dtos.DepenseResponse;
import com.gsautoecole.servicedepenses.dtos.EmployeeResponse;
import com.gsautoecole.servicedepenses.dtos.VehiculeResponse;
import com.gsautoecole.servicedepenses.entities.Categorie;
import com.gsautoecole.servicedepenses.entities.Depense;
import com.gsautoecole.servicedepenses.enums.TypeDepense;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepenseServiceImpl implements IDepenseService{
    @Value("${serviceEmp}")
	private String serviceEmp;
	@Value("${serviceVehicule}")
	private String serviceVehicule;

	private IDepenseDao depenseDao;
	private CategorieDepenseDao catDao;

	@Autowired
	private ModelMapper modelmapper;
    @Autowired
	private RestTemplate restTemplate;
	
	public DepenseServiceImpl(IDepenseDao depenseDao,CategorieDepenseDao catDao) {
		this.depenseDao = depenseDao;
		this.catDao = catDao;
	}

    @Override
    public DepenseResponse getDepenseByid(Long idAuto,Long id){
		Depense depense = depenseDao.findByAutoEcoleIdAndId(idAuto, id);
		if(depense != null){
			DepenseResponse depenseResponse = modelmapper.map(depense, DepenseResponse.class);
			if(depense.getEmpId()!=null){
				Map<String, Long> uriVariables = new HashMap<>();
			    uriVariables.put("idAuto", idAuto);
			    uriVariables.put("id", depense.getEmpId());
				EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
				if(emp != null){
					depenseResponse.setEmp(emp);
			        //return depenseResponse;
				}else{
					throw new EntityNotFoundException("Cet employee n'a pas été trouvé !");
				}
			}else if(depense.getVehiculeId()!=null){
			    VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, depense.getVehiculeId());
				if(vehicule != null){
					depenseResponse.setVehicule(vehicule);
			        //return depenseResponse;
				}else{
					throw new EntityNotFoundException("Cette vehicule n'a pas été trouvé !");
				}
			}
			return depenseResponse;
		}else{
			throw new EntityNotFoundException("depense avec ID "+id+" n'a pas été trouvé");
		}
	} 

	// @Override
	// public Depense getDepenseByAutoEcole(Long idAuto,Long idDep) {
	// 	System.out.println("idDep :"+idDep);
	// 	if(depenseDao.findByAutoEcoleIdAndId(idAuto, idDep) == null) {
	// 		throw new EntityNotFoundException("Cette depense n'a pas été trouvé");
	// 	}
	// 	return depenseDao.findByAutoEcoleIdAndId(idAuto, idDep);
	// }

	//@Override
	// public DepenseResponse getDepByAutoEcole(Long idAuto,Long idDep) {
	// 	System.out.println("idDep :"+idDep);
	// 	if(depenseDao.findByAutoEcoleIdAndId(idAuto, idDep) == null) {
	// 		throw new EntityNotFoundException("Cette depense n'a pas été trouvé");
	// 	}
	// 	return null;
	// }
	@Override
	public List<DepenseResponse> getAllDepensesByAutoEcole(Long idAuto) {
		List<Depense> depenses = depenseDao.findByAutoEcoleId(idAuto);
		if(!depenses.isEmpty()){
			List<DepenseResponse> depenseResponseList = new ArrayList<>();
			depenses.forEach(depense->{
				DepenseResponse depenseResponse = modelmapper.map(depense, DepenseResponse.class);
				if(depense.getEmpId()!=null){
					Map<String, Long> uriVariables = new HashMap<>();
					uriVariables.put("idAuto", idAuto);
					uriVariables.put("id", depense.getEmpId());
					EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
					if(emp != null){
						depenseResponse.setEmp(emp);
						depenseResponseList.add(depenseResponse);
					}else{
						throw new EntityNotFoundException("Cet employee n'a pas été trouvé !");
					}
				}else if(depense.getVehiculeId()!=null){
					VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, depense.getVehiculeId());
					if(vehicule != null){
						depenseResponse.setVehicule(vehicule);
						depenseResponseList.add(depenseResponse);
					}else{
						throw new EntityNotFoundException("Cette vehicule n'a pas été trouvé !");
					}
				}
				
			});
			return depenseResponseList;
		}else{
			throw new EntityNotFoundException("il n'existe pas de depenses pour cette auto-ecole !");
		}
		
	}
    
	@Override
	public List<Depense> getAllDepensesByAutoEcoleAndEmp(Long idAuto,Long idEmp) {
		
		return depenseDao.findByAutoEcoleIdAndEmpId(idAuto,idEmp);
	}
	
	@Override
	public List<Depense> getAllDepensesByAutoEcoleAndVehicule(Long idAuto,Long idVeh) {
		return depenseDao.findByAutoEcoleIdAndVehiculeId(idAuto, idVeh);

	}
	
	@Override
	public List<DepenseResponse> getAllDepensesByAutoEcoleAndTypeCat(Long idAuto,TypeDepense type) {
		List<Depense> allDepenses = depenseDao.findByAutoEcoleId(idAuto);
		List<DepenseResponse> depensesUtiles = new ArrayList<>();
		if(type == TypeDepense.Personnel) {
			allDepenses.forEach(depense->{
				DepenseResponse depenseResponse = modelmapper.map(depense, DepenseResponse.class);
				if(depense.getCategorie().getTypeDep()==TypeDepense.Personnel){
					Map<String, Long> uriVariables = new HashMap<>();
					uriVariables.put("idAuto", idAuto);
					uriVariables.put("id", depense.getEmpId());
					EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
					if(emp != null){
						depenseResponse.setEmp(emp);
						depensesUtiles.add(depenseResponse);
					}else{
						throw new EntityNotFoundException("Un employee n'a pas été trouvé !");
					}
				}
			});
			return depensesUtiles;
		}else if(type == TypeDepense.Local) {
			allDepenses.forEach(depense->{
				DepenseResponse depenseResponse = modelmapper.map(depense, DepenseResponse.class);
				if(depense.getCategorie().getTypeDep()==TypeDepense.Local)
					depensesUtiles.add(depenseResponse);
			});
			return depensesUtiles;
		}else if(type == TypeDepense.Véhicule) {
			allDepenses.forEach(depense->{
				DepenseResponse depenseResponse = modelmapper.map(depense, DepenseResponse.class);
				if(depense.getCategorie().getTypeDep()==TypeDepense.Véhicule){
					VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, depense.getVehiculeId());
					if(vehicule != null){
						depenseResponse.setVehicule(vehicule);
						depensesUtiles.add(depenseResponse);
					}else{
						throw new EntityNotFoundException("Cette vehicule n'a pas été trouvé !");
					}
				}    
			});
			return depensesUtiles;
		}else if(type == TypeDepense.General) {
			allDepenses.forEach(depense->{
				DepenseResponse depenseResponse = modelmapper.map(depense, DepenseResponse.class);
				depensesUtiles.add(depenseResponse);
			});
			return depensesUtiles;
		}else {
			throw new EntityNotFoundException("Le type de catégorie donné  n'a pas été trouvé !");
		}
	}
	
	@Override
	public Depense addDepenseAutoEcole(Long idAuto, Depense depense) {
		Categorie catDepense = catDao.findById(depense.getCategorie().getId()).get();
		if(catDepense.getTypeDep().toString().equals("Personnel")) {
			Map<String, Long> uriVariables = new HashMap<>();
			uriVariables.put("idAuto", idAuto);
			uriVariables.put("id", depense.getEmpId());
			EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
			List<Categorie> categories = catDao.findByTypeDep(TypeDepense.Personnel);
			if(categories.contains(catDepense) && catDao.findByAutoEcoleId(idAuto).contains(catDepense) ) {
				System.out.println("type personnel");
				if(emp!=null) {
					System.out.println("ca marche");
					depense.setEmpId(emp.getId());
					depense.setAutoEcoleId(idAuto);
					depense.setDesignation(emp.getNom_emp()+" "+emp.getPrenom_emp()+"("+catDepense.getCategName()+")");
					depense.setVehiculeId(null);
					return depenseDao.save(depense);
				}else {
					throw new EntityNotFoundException("Cet employé n'a pas été trouvé !");
				}
			}else {
				throw new NullPointerException("Problème ou niveau de catégorie de dépense donnée !");
			}
		}else if(catDepense.getTypeDep().toString().equals("Local")) {
			System.out.println("type local");
			List<Categorie> categories = catDao.findByTypeDep(TypeDepense.Local);
			if(catDepense != null && catDao.findByAutoEcoleId(idAuto).contains(catDepense)&& categories.contains(catDepense)) {
				System.out.println("ca marche");
				depense.setAutoEcoleId(idAuto);
				depense.setDesignation(catDepense.getCategName());
				depense.setEmpId(null);
				depense.setVehiculeId(null);
				return depenseDao.save(depense);
			}else {
				throw new NullPointerException("Problème ou niveau de catégorie de dépense donnée !");
			}
		}else if(catDepense.getTypeDep().toString().equals("Véhicule")) {
			System.out.println("type vehicule");
			List<Categorie> categories = catDao.findByTypeDep(TypeDepense.Véhicule);
			VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, depense.getVehiculeId());
			if(catDepense != null && catDao.findByAutoEcoleId(idAuto).contains(catDepense) && categories.contains(catDepense)) {
				if(depense.getVehiculeId()!=null && vehicule!= null) {
					System.out.println("ca marche");
					depense.setAutoEcoleId(idAuto);;
					depense.setDesignation(String.valueOf(vehicule.getMatricule()));
					depense.setEmpId(null);;
					return depenseDao.save(depense);
				}else {
					throw new EntityNotFoundException("Cette véhicule n'existe pas !");
				}
				
			}else {
				throw new NullPointerException("Problème ou niveau de catégorie de dépense donnée !");
			}
		}else {
			throw new EntityNotFoundException("Cette categorie n'a pas été trouvé !");
		}

	}	
	@Override
	public Depense updateDepenseAutoEcole(Long idAuto, Depense Nvdepense,Long idDep) {
		
		AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
		Categorie c = catDao.findById(Nvdepense.getCategorie().getId()).orElse(null);
		
		//Employee emp = empDao.findByAutoEcoleIdAndId(idAuto, Nvdepense.getEmp().getId());
		if(c.getTypeDep().toString().equals("Personnel")) {
			Map<String, Long> uriVariables = new HashMap<>();
			uriVariables.put("idAuto", idAuto);
			uriVariables.put("id", Nvdepense.getEmpId());
			EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
			if(auto != null && c != null) {
				if(catDao.findByAutoEcoleId(idAuto).contains(c)  && emp != null) {
					Nvdepense.setAutoEcoleId(idAuto);;
					Nvdepense.setCategorie(c);
					Nvdepense.setId(idDep);
					Nvdepense.setVehiculeId(null);
					Nvdepense.setDesignation(emp.getNom_emp()+" "+emp.getPrenom_emp()+"("+c.getCategName()+")");
					return depenseDao.save(Nvdepense);
				}else {
					throw new IllegalArgumentException("La catégorie ou l'employé ne correspond pas à cette auto-école !");
				}
			}else {
				throw new EntityNotFoundException("L'auto-ecole ou la categorie n'a pas été trouvé !");
			}
		}else if(c.getTypeDep().toString().equals("Local")) {
			if(auto != null && c != null) {
				if(catDao.findByAutoEcoleId(idAuto).contains(c)){
					Nvdepense.setAutoEcoleId(idAuto);;
					Nvdepense.setCategorie(c);
					Nvdepense.setId(idDep);
					Nvdepense.setDesignation(c.getCategName());
					Nvdepense.setEmpId(null);
					Nvdepense.setVehiculeId(null);
					return depenseDao.save(Nvdepense);
				}else {
					throw new IllegalArgumentException("L'auto-ecole n'existe pas,ou la catégorie ne correspond pas à cette auto-école !");
				}
			}
		
		}else if(c.getTypeDep().toString().equals("Véhicule")) {
			VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, Nvdepense.getVehiculeId());
			if(auto != null && c != null) {
				if(catDao.findByAutoEcoleId(idAuto).contains(c) && vehicule != null) {
					Nvdepense.setAutoEcoleId(idAuto);;
					Nvdepense.setCategorie(c);
					Nvdepense.setId(idDep);
					Nvdepense.setDesignation(String.valueOf(vehicule.getMatricule()));
					Nvdepense.setEmpId(null);
					return depenseDao.save(Nvdepense);
				}else {
					throw new IllegalArgumentException("L'auto-ecole n'existe pas,ou la catégorie ne correspond pas à cette auto-école !");
				}
			}
			
		}else {
			throw new IllegalArgumentException("Type depense n'existe pas !");
		}
		return depenseDao.findByAutoEcoleIdAndId(idAuto, idDep);
}

	@Override
	public void deleteDepenseAutoEcole(Long idAuto, Long idDep) {
		Depense depense = depenseDao.findByAutoEcoleIdAndId(idAuto, idDep);
		if(depense != null) {
			depenseDao.deleteById(idDep);
		}else {
			throw new EntityNotFoundException("La dépense n'a pas été trouvé pour cette auto-école !");
		}
		
		
	}
	
	@Override
	public DepenseDTO getAllDepenseAutoEcoleBetweenDates(Long idAuto,TypeDepense type, LocalDate datedebut,LocalDate dateFin){
		AutoResponse auto = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcoles/{id}",AutoResponse.class, idAuto);
		List<Depense> depenses=depenseDao.findByAutoEcoleIdAndCategorieTypeDepAndDateDepBetween(idAuto,type, datedebut, dateFin);
		List<Map<String, String>> depensesutiles=new ArrayList<>();
		int total =0;
		DepenseDTO personneldep = new DepenseDTO();
		if(type.toString().equals(TypeDepense.Personnel.name())) {
			personneldep.setAuto_name(auto.getNomAuto());
			personneldep.setAdresse(auto.getAdresse());
			personneldep.setEmail(auto.getEmail());
			personneldep.setFax(auto.getFax());
			personneldep.setLogo_auto(auto.getLogoAuto());
			personneldep.setTel(auto.getTel());
			depenses.forEach(depense->{
				Map<String, String> depInfo = new HashMap<>();
				Map<String, Long> uriVariables = new HashMap<>();
				uriVariables.put("idAuto", idAuto);
				uriVariables.put("id", depense.getEmpId());
			    EmployeeResponse emp = restTemplate.getForObject("http://"+serviceEmp+":8088/AutoEcole/{idAuto}/Employees/{id}", EmployeeResponse.class, uriVariables);
				depInfo.put("employe", emp.getNom_emp()+" "+emp.getPrenom_emp());
				depInfo.put("montant",String.valueOf(depense.getMontant()));
				depInfo.put("date", String.valueOf(depense.getDateDep()));
				depInfo.put("categorie", depense.getCategorie().getCategName());
				depensesutiles.add(depInfo);
				
			});
			personneldep.setDepenses(depensesutiles);
			// Calculer le total des montants
			for(Depense depense:depenses) {
				total += depense.getMontant();
			}
			personneldep.setTotal(total);
			return personneldep;
		}else if(type.toString().equals(TypeDepense.Local.name())) {
			personneldep.setAuto_name(auto.getNomAuto());
			personneldep.setAdresse(auto.getAdresse());
			personneldep.setEmail(auto.getEmail());
			personneldep.setFax(auto.getFax());
			personneldep.setLogo_auto(auto.getLogoAuto());
			personneldep.setTel(auto.getTel());
			depenses.forEach(depense->{
				Map<String, String> depInfo = new HashMap<>();
				depInfo.put("montant",String.valueOf(depense.getMontant()));
				depInfo.put("date", String.valueOf(depense.getDateDep()));
				depInfo.put("categorie", depense.getCategorie().getCategName());
				depensesutiles.add(depInfo);
				
			});
			personneldep.setDepenses(depensesutiles);
			for(Depense depense:depenses) {
				total += depense.getMontant();
			}
			personneldep.setTotal(total);
			return personneldep;
		}else if(type.toString().equals(TypeDepense.Véhicule.name())) {
			personneldep.setAuto_name(auto.getNomAuto());
			personneldep.setAdresse(auto.getAdresse());
			personneldep.setEmail(auto.getEmail());
			personneldep.setFax(auto.getFax());
			personneldep.setLogo_auto(auto.getLogoAuto());
			personneldep.setTel(auto.getTel());
			depenses.forEach(depense->{
				Map<String, String> depInfo = new HashMap<>();
				VehiculeResponse vehicule = restTemplate.getForObject("http://"+serviceVehicule+":8900/vehicles/show-vehicle-by-id/{id}", VehiculeResponse.class, depense.getVehiculeId());
				depInfo.put("montant",String.valueOf(depense.getMontant()));
				depInfo.put("date", String.valueOf(depense.getDateDep()));
				depInfo.put("categorie", depense.getCategorie().getCategName());
				depInfo.put("vehicule", vehicule.getMatricule()+":"+vehicule.getMarque());
				depensesutiles.add(depInfo);
				
			});
			personneldep.setDepenses(depensesutiles);
			for(Depense depense:depenses) {
				total += depense.getMontant();
			}
			personneldep.setTotal(total);
			return personneldep;
		}else if(type.toString().equals(TypeDepense.General.name())) {
			List<Depense> depensesGeneral = depenseDao.findByAutoEcoleIdAndDateDepBetween(idAuto, datedebut, dateFin);
			personneldep.setAuto_name(auto.getNomAuto());
			personneldep.setAdresse(auto.getAdresse());
			personneldep.setEmail(auto.getEmail());
			personneldep.setFax(auto.getFax());
			personneldep.setLogo_auto(auto.getLogoAuto());
			personneldep.setTel(auto.getTel());
			depensesGeneral.forEach(depense->{
				Map<String, String> depInfo = new HashMap<>();
				depInfo.put("montant",String.valueOf(depense.getMontant()));
				depInfo.put("date", String.valueOf(depense.getDateDep()));
				depInfo.put("categorie", depense.getCategorie().getTypeDep().name());
				depInfo.put("designation", depense.getDesignation());
				depensesutiles.add(depInfo);
				
			});
			personneldep.setDepenses(depensesutiles);
			for(Depense depense:depensesGeneral) {
				total += depense.getMontant();
			}
			personneldep.setTotal(total);
			return personneldep;
		}else {
			throw new EntityNotFoundException("La dépense n'a pas été trouvé pour cette auto-école !");
		}

		
	}
	
	@Override
	public List<DepenseResponse> getGroupOfDepenses(Long idAuto, List<Long> ids) {
		List<DepenseResponse> depenses = new ArrayList<>();
		ids.forEach(id -> {
			DepenseResponse depense = getDepenseByid(idAuto, id);
			if(depense != null) {
				depenses.add(depense);
			}	
		});
		if(depenses.size() == ids.size()) {
			return depenses;
		}else {
			throw new EntityNotFoundException("Les dépenses n'a pas été tous trouvé !");
		}

	}

}
