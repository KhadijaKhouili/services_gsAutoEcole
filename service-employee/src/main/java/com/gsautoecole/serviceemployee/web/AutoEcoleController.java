package com.gsautoecole.serviceemployee.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gsautoecole.serviceemployee.Entities.AutoEcole;
import com.gsautoecole.serviceemployee.dao.AutoEcoleDao;

import jakarta.validation.Valid;

@RestController
public class AutoEcoleController {
	
	@Autowired
	private AutoEcoleDao dao;
	
	@GetMapping("/AutoEcoles")
	public ResponseEntity<List<AutoEcole>>  getAllAutos() {
		return new ResponseEntity<List<AutoEcole>>(dao.findAll(), HttpStatus.OK) ;
	}
	
	@GetMapping("/AutoEcoles/{id}")
	public ResponseEntity<AutoEcole> getAutoEcole(@PathVariable Long id) {
		return new ResponseEntity<AutoEcole>(dao.findById(id).get(), HttpStatus.OK) ;
	}
	
	@PostMapping("/AutoEcoles/add")
	public ResponseEntity<HttpStatus> saveEmployee(@Valid @RequestBody AutoEcole auto) {
		 dao.save(auto);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
//	@PutMapping("/Employees/{id}/update")
//	public void updateEmployee(@PathVariable Long id,@Valid @RequestBody Employee employee) {
//		employee.setId(id);
//		empService.updateEmploye(employee);
//	}
	
//	@DeleteMapping("Employees/{id}/delete")
//	public void deleteEmployee(@PathVariable Long id) {
//		empService.deleteEmployee(id);;
//	}
	
}
