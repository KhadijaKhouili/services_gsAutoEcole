package com.gsautoecole.serviceemployee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsautoecole.serviceemployee.Entities.Absence;
import com.gsautoecole.serviceemployee.Entities.Employee;
import com.gsautoecole.serviceemployee.Exceptions.EmployeeAlreadyExistsException;
import com.gsautoecole.serviceemployee.dao.AutoEcoleDao;
import com.gsautoecole.serviceemployee.dao.EmployeeDao;
import com.gsautoecole.serviceemployee.enums.TypeEmploie;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{
	
   private EmployeeDao empDao;
   private AutoEcoleDao autoDao;
    
	public EmployeeServiceImp(EmployeeDao empDao,AutoEcoleDao autoDao) {
		this.empDao=empDao;
		this.autoDao=autoDao;
	}
	
    
	// Retourner tous les employes d'une auto-ecole
	@Override
	public List<Employee> getAllEmployeesAutoEcole(Long id) {
		// TODO Auto-generated method stub
		
		return empDao.findByAutoEcoleId(id);
	}
	
	// Retourner l'employe avec l'id "idEmp" dans l'auto-ecole qui possede l'id "idAuto"
	@Override
	public Employee getAutoEcoleEmployeeById(Long idAuto,Long idEmp) {
		// TODO Auto-generated method stub
		
		if( empDao.findByAutoEcoleIdAndId(idAuto, idEmp) == null) {
			throw new EntityNotFoundException("Employé avec l'ID " + idEmp + " n'a pas été trouvé");
		}
		return empDao.findByAutoEcoleIdAndId(idAuto, idEmp);
	}
	
	// Ajoute un employee "emp" a l'auto-ecole qui possede l'id "idAuto"
	@Override
	public Employee addEmployeeAutoEcole(Long idAuto,Employee emp){
		
		try {
				    
		    // Vérifiez si les informations de l'employé existent déjà dans la base de données
		    if (empDao.findByCINAndAutoEcoleId(emp.getCIN(),idAuto) != null) {
		        throw new EmployeeAlreadyExistsException(" L'employé avec CIN "+emp.getCIN()+" existe déjà dans cette auto-école !");
		    }
		    if((emp.getTypeEmp().getTpemploie()).equals(TypeEmploie.Directeur.toString()) && empDao.findByTypeEmpTpemploieAndAutoEcoleId(TypeEmploie.Directeur.name(), idAuto) != null) {
		    	throw new EmployeeAlreadyExistsException("Il existe déjà un directeur pour cette auto-école!");
		    }
		    // Si les informations n'existent pas déjà, enregistrez l'employé dans la base de données
		    emp.setAutoEcoleId(idAuto);
		    emp.setEtat("ACTIVE");
		    return empDao.save(emp);
		} catch (DataIntegrityViolationException ex) {
		    // Traitez d'autres exceptions liées à l'intégrité des données si nécessaire
		    throw new RuntimeException("Une erreur d'intégrité des données s'est produite lors de l'ajout de l'employé", ex);
		}
	}
	
	// Fait une mise a jour des infos de l'employe "emp" possede id "idEmp" existe dans l'auto-ecole avec l'id "idAuto"
	@Override
	public void updateEmployeAutoEcole(Long idAuto,Long idEmp,Employee emp) {
		Employee e = empDao.findByAutoEcoleIdAndId(idAuto, idEmp);
			if( e != null) {
				// verifie si cette auto-ecole possede deja un directeur , si oui le mise a jour ne dois pas etre effectuee ! 
				if(emp.getTypeEmp().toString().equals(TypeEmploie.Directeur.name()) && empDao.findByTypeEmpTpemploieAndAutoEcoleId(TypeEmploie.Directeur.name(), idAuto) != null) {
					throw new EmployeeAlreadyExistsException("Il existe déjà un directeur pour cette auto-école!");
				}	
	        System.out.println("existe : "+e);
			emp.setId(idEmp);
			emp.setAutoEcoleId(idAuto);
		    empDao.save(emp);
		}else {
			throw new EntityNotFoundException("Employé avec l'ID " + idEmp + " n'a pas été trouvé dans cette auto-école!");
		}
	}
	
	@Override
	public void deleteEmployeeAutoEcole(Long idAuto,Employee emp) {
		emp.setAutoEcoleId(idAuto);
		
		if(autoDao.findById(idAuto).get().getEmployees().contains(emp.getId())) {
			Long idEmp = emp.getId();
			System.out.println(idEmp);
			empDao.deleteById(idEmp);
		}else {
			throw new EntityNotFoundException("Cet emmployé n'a pas été trouvé pour cette auto-ecole !");
		}
		//if(empDao.findByAutoEcoleIdAndId(idAuto, idEmp) != null ){
				
	}
	@Override
	public void deleteEmployeeAutoEcoleV2(Long idAuto,Long idEmp) {
		Employee e = empDao.findByAutoEcoleIdAndId(idAuto, idEmp);
		if(e != null ){
			e.setEtat("SUPPRIME");
		}else {
			throw new EntityNotFoundException("Employé avec l'ID " + idEmp + " n'a pas été trouvé pour cette auto-ecole !");
		}
	}
	@Override
	public void ArchivedEmployeeAutoEcoleV2(Long idAuto,Long idEmp) {
		Employee e = empDao.findByAutoEcoleIdAndId(idAuto, idEmp);
		if(e != null ){
			e.setEtat("ARCHIVE");
		}else {
			throw new EntityNotFoundException("Employé avec l'ID " + idEmp + " n'a pas été trouvé pour cette auto-ecole !");
		}
	}
	
	// Retourner tous les Absences d'une Auto-ecole
	@Override
	 public List<Absence> getAllAbsencesByAutoEcole(Long autoId) {
	        List<Absence> allAbsences = new ArrayList<>();
	        
	        List<Employee> employees = empDao.findByAutoEcoleId(autoId);
	        for (Employee employee : employees) {
	            allAbsences.addAll(employee.getAbsences());
	        }
	        allAbsences.forEach(abs->{
	        	System.out.println(abs.getEmp());
	        });
	        return allAbsences;
	    }


	@Override
	public void deleteEmployeeAutoEcole(Long idAuto, Long idEmp) {
		//empDao.deleteById(id);
		Employee employee = empDao.findByAutoEcoleIdAndId(idAuto, idEmp);
		if(employee != null) {
			empDao.delete(employee);
		}else {
			throw new EntityNotFoundException("Cet employé n'a pas été trouvé pour cette auto-ecole !");
		}
	 
	}

	@Override
	public List<Employee> getGroupOfEmployee(Long idAuto, List<Long> ids) {
		List<Employee > employees = new ArrayList<>();
		ids.forEach(id -> {
			Employee emp = empDao.findByAutoEcoleIdAndId(idAuto, id);
			if(emp != null) {
				employees.add(emp);
			}	
		});
		if(employees.size() == ids.size()) {
			return employees;
		}else {
			throw new EntityNotFoundException("Les employés n'a pas été tous trouvé !");
		}

	}
}
