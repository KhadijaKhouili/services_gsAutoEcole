package com.gsautoecole.serviceemployee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gsautoecole.serviceemployee.Entities.Employee;

//@RepositoryRestResource
public interface EmployeeDao extends JpaRepository<Employee,Long>{
	
	public Employee findByCIN(String cin);
	
	public Employee findByCINAndAutoEcoleId(String cin, Long idAuto);
	
	public Employee findByTypeEmpTpemploieAndAutoEcoleId(String typeEmploie, Long idAuto);
	
	public List<Employee> findByAutoEcoleId(Long id_Auto);
	
	public List<Employee> findByNomEmp(String nomEmp);
	
	public Employee findByAutoEcoleIdAndId(Long idAuto,Long IdEmp);
	
	// @Query("SELECT e FROM Employee e WHERE " +
    //         "e.nomEmp LIKE %:searchQuery% OR " +
    //         "e.prenomEmp LIKE %:searchQuery% OR " +
    //         "e.dateNaissEmp LIKE %:searchQuery% OR " +
    //         "e.tel LIKE %:searchQuery%")
    // List<Employee> searchEmployeesByQuery(String searchQuery);

//    List<Employee> findByNomEmpContainingOrPrenomEmpContainingOrdateNaissEmpContaining(Object donnee);
}
