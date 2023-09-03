package com.gsautoecole.serviceemployee.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsautoecole.serviceemployee.Entities.Absence;
import com.gsautoecole.serviceemployee.Entities.CategorieMonit;
import com.gsautoecole.serviceemployee.Entities.Employee;
import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.dtos.Request;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;
import com.gsautoecole.serviceemployee.enums.TypeMoniteur;
import com.gsautoecole.serviceemployee.services.EmployeeService;
import com.gsautoecole.serviceemployee.services.ICategorieService;
import com.gsautoecole.serviceemployee.services.ItypeEmploieService;
import com.gsautoecole.serviceemployee.services.MoniteurService;

import jakarta.validation.Valid;

//@RequestMapping("/api/AutoEcole")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	@Autowired
	private MoniteurService monitService;
	@Autowired
	private ItypeEmploieService typeservice;

	@Autowired
	private ICategorieService catService;
	
	// @GetMapping("/AutoEcole/Employees")
	// public ResponseEntity<List<Employee>> getAllEmployeesAssociesAutoEcole(@RequestAttribute("auto_Id") Long idAuto) {

	// 	return new ResponseEntity<List<Employee>>(empService.getAllEmployeesAutoEcole(idAuto),HttpStatus.OK);
	// }
	@GetMapping("/AutoEcole/AllEmployees/{idAuto}")
	public ResponseEntity<List<Employee>> getAllEmployeesAssocies(@PathVariable Long idAuto) {

		return new ResponseEntity<List<Employee>>(empService.getAllEmployeesAutoEcole(idAuto),HttpStatus.OK);
	}
	@GetMapping("/")
	public String getmsg(@RequestAttribute("auto_Id") Long id) {
		System.out.println("id : "+id);
		return "khadija lghzala lah y7jbha :)";
	}
//	@GetMapping("/AutoEcole/Moniteurs/{id}")
//	    public List<Categorie> getAllMoniteurs(@PathVariable Long id) {
//		System.out.println(catService.getAllCategoriesMoniteur(id));
//	        return catService.getAllCategoriesMoniteur(id);
//	    }
//	@GetMapping("/AutoEcole/MoniteursAbs/{id}")
//    public Employee getAllAbsenceEmp(@PathVariable Long id) {
//	//System.out.println(catService.getAllCategoriesMoniteur(id));
//        return absService.getAbsences(id);
//    }
//	public ResponseEntity<List<Moniteur>> getAllMoniteursAssociesAutoEcole() {
//        System.out.println(monitService.getAllMoniteurs().get(0).getId());
//		return new ResponseEntity<List<Moniteur>>(monitService.getAllMoniteurs(),HttpStatus.OK);
//	}
	
	
	// GET ALL ABSENCES OF A SPECIFIC DRIVING SCHOOL
	@GetMapping("/AutoEcole/Absences")
    public List<Absence> getAllAbsenceAutoEcole(@RequestAttribute("auto_Id") Long id) {
//	System.out.println(catService.getAllCategoriesMoniteur(id));
		
        return empService.getAllAbsencesByAutoEcole(id);
    }
	
	//GET A SPECEFIC EMPLOYE FROM A SPECEFIC DRIVING SCHOOL
	@GetMapping("/AutoEcole/Employees")
	public ResponseEntity<Employee> getEmployeeAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		return new ResponseEntity<Employee>(empService.getAutoEcoleEmployeeById(idAuto, request.getId()),HttpStatus.OK);
	}
	
	// ADD A SPECEFIC EMPLOYE FROM A SPECEFIC DRIVING SCHOOL
	@PostMapping("/AutoEcole/Employees/add")
	public  ResponseEntity<Employee> saveEmployeeAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@Valid @RequestBody Employee emp) {
		typeservice.addTypeEmploie(emp, emp.getTypeEmp().getTpemploie());
		return new ResponseEntity<Employee>(empService.addEmployeeAutoEcole(idAuto,emp),HttpStatus.CREATED);
	}
    
	// UPDATE SPECEFIC EMPLOYE FROM A SPECEFIC DRIVING SCHOOL
	@PutMapping("/AutoEcole/Employees/update")
	public ResponseEntity<HttpStatus> updateEmployeeAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@Valid @RequestBody Employee employee) {
		//employee.setId(id);
		typeservice.addTypeEmploie(employee,employee.getTypeEmp().getTpemploie());
		empService.updateEmployeAutoEcole(idAuto, employee.getId(), employee);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	// ADD A SPECEFIC MONITOR FROM A SPECEFIC DRIVING SCHOOL
	@PostMapping("/AutoEcole/Employees/addMoniteur")
	public ResponseEntity<HttpStatus> saveMoniteurAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@Valid @RequestBody Moniteur monit) { 
		typeservice.addTypeEmploie(monit, monit.getTypeEmp().getTpemploie());
		monitService.addMoniteurAutoEcole(idAuto, monit);
		 return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	// DELETE A SPECEFIC EMPLOYE EXIST IN A SPECEFIC DRIVING SCHOOL
	@DeleteMapping("/AutoEcole/Employees/delete")
	public ResponseEntity<HttpStatus> deleteEmployeeAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		empService.deleteEmployeeAutoEcole(idAuto, request.getId());
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT) ;
	}
	
	// ARCHIVE A SPECEFIC EMPLOYE FROM A SPECEFIC DRIVING SCHOOL
	@DeleteMapping("/AutoEcole/Employees/Archive")
	public ResponseEntity<HttpStatus> ArchivedEmployeeAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		empService.ArchivedEmployeeAutoEcoleV2(idAuto, request.getId());
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT) ;
	}
	
	// UPDATE A SPECEFIC MONITOR FROM A SPECEFIC DRIVING SCHOOL
	@PutMapping("/AutoEcole/Employees/updateMonit")
	public ResponseEntity<HttpStatus> updateMoniteurAutoEcole(@RequestAttribute("auto_Id") Long idAuto,@Valid @RequestBody Moniteur monit) {
		monit.setId(monit.getId());
		typeservice.addTypeEmploie(monit, monit.getTypeEmp().getTpemploie());
		monitService.updateMoniteurAutoEcole(idAuto, monit.getId(), monit);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("/moniteurPratique/{idm}")
	public ResponseEntity<Moniteur> GetMoniteurPratique( @PathVariable Long idm,@RequestAttribute("auto_Id") Long idAuto  ) {
		return new ResponseEntity<Moniteur>(monitService.getMoniteurByType(idm, idAuto, TypeMoniteur.Pratique),HttpStatus.OK) ;
	}
	@GetMapping("/moniteurTheorique/{idm}")
	public ResponseEntity<Moniteur> GetMoniteurTheorique( @PathVariable Long idm,@RequestAttribute("auto_Id") Long idAuto  ) {
		return new ResponseEntity<Moniteur>(monitService.getMoniteurByType(idm, idAuto, TypeMoniteur.Theorique),HttpStatus.OK);
	}

	@GetMapping("/categories/{libelle}/moniteurs") // par autoecole
	public ResponseEntity<?> getMoniteursByCategorie(@PathVariable TypeCategorie libelle, @RequestAttribute("auto_Id") Long idA, @RequestParam(required = false) TypeMoniteur typeMoniteur) {
	    Map<Long, String> moniteurMap;
	    CategorieMonit categorie = catService.getCategorieByLibelle(idA, libelle); //khas service 
	    if(categorie==null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cette categorie n'existe pas ,entrez une categorie valide "); 

	    }
	    if (typeMoniteur == null) {
	        moniteurMap = monitService.getMoniteursByCategoriesLibelle(libelle, idA);
	    } else {
	        moniteurMap = monitService.findByCategoriesLibelleAndType(libelle, typeMoniteur, idA);
	    }
	    return ResponseEntity.ok(moniteurMap);
	}
	//**************************** SERVICES ROUTES *****************************************

    //GET A SPECEFIC EMPLOYE FROM A SPECEFIC DRIVING SCHOOL
	@GetMapping("/AutoEcole/{idAuto}/Employees/{id}")
	public ResponseEntity<Employee> getEmployeeByAutoEcole(@PathVariable Long idAuto,@PathVariable Long id) {
		return new ResponseEntity<Employee>(empService.getAutoEcoleEmployeeById(idAuto, id),HttpStatus.OK);
	}
	
	@GetMapping("/AutoEcole/{idAuto}/getEmpByIds")
	public ResponseEntity<List<Employee>> getEmployeeseByGroupOfids(@PathVariable Long idAuto,@RequestBody List<Long> ids) {
		return new ResponseEntity<List<Employee>>(empService.getGroupOfEmployee(idAuto, ids),HttpStatus.OK);
	}

	@PostMapping("/getEmpByIds")
	public ResponseEntity<List<Employee>> getEmployeesAutoEcoleByGroupOfids(@RequestAttribute("auto_Id") Long idAuto,@RequestBody List<Long> ids) {
		return new ResponseEntity<List<Employee>>(empService.getGroupOfEmployee(idAuto, ids),HttpStatus.OK);
	}
	
	@PostMapping("/getEmployee")
	public ResponseEntity<Employee> getEmployeeById(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		return new ResponseEntity<Employee>(empService.getAutoEcoleEmployeeById(idAuto, request.getId()),HttpStatus.OK);
	}
	
}
