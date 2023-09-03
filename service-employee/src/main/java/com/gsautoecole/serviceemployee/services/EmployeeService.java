package com.gsautoecole.serviceemployee.services;

import java.util.List;

import com.gsautoecole.serviceemployee.Entities.Absence;
import com.gsautoecole.serviceemployee.Entities.Employee;


public interface EmployeeService {
	
	public List<Employee> getAllEmployeesAutoEcole(Long id);
	
	public Employee getAutoEcoleEmployeeById(Long idAuto,Long idEmp);
	
	public Employee addEmployeeAutoEcole(Long idAuto,Employee emp);
	
	public void updateEmployeAutoEcole(Long idAuto,Long idEmp,Employee emp);
	
	public void deleteEmployeeAutoEcole(Long idAuto,Employee emp) ;

	void deleteEmployeeAutoEcoleV2(Long idAuto, Long idEmp);

	void ArchivedEmployeeAutoEcoleV2(Long idAuto, Long idEmp);

	public List<Absence> getAllAbsencesByAutoEcole(Long autoId);

	public void deleteEmployeeAutoEcole(Long idAuto, Long id);

	public List<Employee> getGroupOfEmployee(Long idAuto, List<Long> ids);

	

}
