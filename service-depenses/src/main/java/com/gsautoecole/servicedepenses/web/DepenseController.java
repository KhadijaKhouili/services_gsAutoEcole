package com.gsautoecole.servicedepenses.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsautoecole.servicedepenses.dtos.DepenseDTO;
import com.gsautoecole.servicedepenses.dtos.DepenseResponse;
import com.gsautoecole.servicedepenses.dtos.RequestDTO;
import com.gsautoecole.servicedepenses.entities.Depense;
import com.gsautoecole.servicedepenses.enums.TypeDepense;
import com.gsautoecole.servicedepenses.services.IDepenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/depenses")
public class DepenseController {
	
	@Autowired
	private IDepenseService depService;
	
    @GetMapping("/Depense")
	public ResponseEntity<DepenseResponse> getDepense(@RequestAttribute("auto_Id") Long idAuto,@RequestBody RequestDTO request ) {
		return new ResponseEntity<DepenseResponse>(depService.getDepenseByid(idAuto, request.getId()), HttpStatus.OK)  ;
	}

	@GetMapping("/allInterDepenses")
	public ResponseEntity<DepenseDTO>  getAllDepensesBetweenDates(@RequestAttribute("auto_Id") Long idAuto,@RequestParam TypeDepense type,@RequestParam LocalDate dateDeb,@RequestParam LocalDate datefin) {
		return new ResponseEntity<DepenseDTO>(depService.getAllDepenseAutoEcoleBetweenDates(idAuto, type, dateDeb, datefin), HttpStatus.OK)  ;
	}
	@GetMapping("/allDepenses")
	public ResponseEntity<List<DepenseResponse>> getAllDepensesByType(@RequestAttribute("auto_Id") Long idAuto, @RequestParam TypeDepense type) {
		return new ResponseEntity<List<DepenseResponse>>(depService.getAllDepensesByAutoEcoleAndTypeCat(idAuto, type), HttpStatus.OK)  ;
	}
	
	// @GetMapping("/Depense")
	// public ResponseEntity<Depense> getDepense(@RequestAttribute("auto_Id") Long idAuto,@RequestBody RequestDTO request) {
	// 	return new ResponseEntity<Depense>(depService.getDepenseByAutoEcole(idAuto, request.getId()), HttpStatus.OK)  ;
	// }
    
	@GetMapping("/getByEmp")
	public ResponseEntity<List<Depense>> getDepenseByEmp(@RequestAttribute("auto_Id") Long idAuto,@RequestBody RequestDTO request) {
		return new ResponseEntity<List<Depense>>(depService.getAllDepensesByAutoEcoleAndEmp(idAuto, request.getId()), HttpStatus.OK)  ;
	}
//	
	@GetMapping("/getByVehicule")
	public ResponseEntity<List<Depense>> getDepenseByVehicule(@RequestAttribute("auto_Id") Long idAuto,@RequestBody RequestDTO request) {
		return new ResponseEntity<List<Depense>>(depService.getAllDepensesByAutoEcoleAndVehicule(idAuto, request.getId()), HttpStatus.OK)  ;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Depense> addDepense(@RequestAttribute("auto_Id") Long idAuto,@RequestBody @Valid Depense depense) {
		return new ResponseEntity<Depense>(depService.addDepenseAutoEcole(idAuto, depense),HttpStatus.OK) ;
	}
	
	@PutMapping("/update")
	public ResponseEntity<Depense> updateDepense(@RequestAttribute("auto_Id") Long idAuto,@Valid @RequestBody Depense depense) {
		return new ResponseEntity<Depense>(depService.updateDepenseAutoEcole(idAuto, depense, depense.getId()),HttpStatus.OK) ;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteDepense(@RequestAttribute("auto_Id") Long idAuto,@RequestBody RequestDTO request) {
		depService.deleteDepenseAutoEcole(idAuto, request.getId());
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT) ;
	}
	
	
	//**************************************** SERVICES ROUTES ************************************************
	
	@PostMapping("/getDepensesByIds")
	public ResponseEntity<List<DepenseResponse>> getAbsencesAutoEcoleByGroupOfids(@RequestAttribute("auto_Id") Long idAuto,@RequestBody List<Long> ids) {
		return new ResponseEntity<List<DepenseResponse>>(depService.getGroupOfDepenses(idAuto, ids),HttpStatus.OK);
	}
	
	@PostMapping("/getDepense")
	public ResponseEntity<DepenseResponse> getDepenseById(@RequestAttribute("auto_Id") Long idAuto,@RequestBody RequestDTO request) {
		return new ResponseEntity<DepenseResponse>(depService.getDepenseByid(idAuto, request.getId()), HttpStatus.OK)  ;
	}
	
	
}
