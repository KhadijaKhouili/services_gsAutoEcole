package com.gsautoecole.serviceemployee.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.gsautoecole.serviceemployee.Entities.Absence;
import com.gsautoecole.serviceemployee.Entities.CauseAbsence;
import com.gsautoecole.serviceemployee.dtos.Request;
import com.gsautoecole.serviceemployee.services.IAbsenceService;
import jakarta.validation.Valid;

@RestController
public class AbsenceController {
	
	@Autowired
	private IAbsenceService absenceService;
	
	@GetMapping("/Absences")
	public ResponseEntity<List<Absence>>  getAllAbsences(@RequestAttribute("auto_Id") Long idAuto){
		return new ResponseEntity<List<Absence>>(absenceService.getAllAbsences(idAuto), HttpStatus.OK);
	}
	
	@GetMapping("/Absence")
	public ResponseEntity<Absence>getAbsence(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {	
		return new ResponseEntity<Absence>(absenceService.getAbsenceById(idAuto,request.getId()),HttpStatus.OK);
	}
	@PostMapping("/Absences/add")
	public ResponseEntity<HttpStatus> addAbsence(@RequestAttribute("auto_Id") Long idAuto,@RequestBody @Valid Absence abs) {
		absenceService.addCause(abs, abs.getCauseAbsence().getCause());
		absenceService.addAbsence(idAuto,abs);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED); 
	}
	
//	@PutMapping("/Absences/updateAbs/{id}")
//	public void updateAbsence(@PathVariable Long id,@RequestBody @Valid Absence abs) {
//		abs.setId(id);
//		absenceService.updateAbsence(abs);
//	}
	
	@PutMapping("/Absence/update")
	public ResponseEntity<HttpStatus> updateAbsence(@RequestAttribute("auto_Id") Long idAuto,@RequestBody @Valid Absence abs) {
		//abs.setId(abs.getId());
		//absenceService.addCause(abs, abs.getCauseAbsence().getCause());
		absenceService.updateAbsence(idAuto, abs);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("/Absence/delete")
	public ResponseEntity<HttpStatus> deleteAbsence(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		absenceService.deleteAbsence(idAuto, request.getId());
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}


	
	//*********************** CAUSES D'ABSENCES *****************************
	
	@GetMapping("/CauseAbsences")
	public ResponseEntity<List<CauseAbsence>>  getAllCauseAbsences(@RequestAttribute("auto_Id") Long idAuto){
		return new ResponseEntity<List<CauseAbsence>>(absenceService.getAllCauses(idAuto), HttpStatus.OK) ;
	}
	
	@GetMapping("/CauseAbsence")
	public ResponseEntity<CauseAbsence> getCauseAbsence(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		return new ResponseEntity<CauseAbsence>(absenceService.getCauseById(idAuto,request.getId()),HttpStatus.OK);
	}
//	@PostMapping("/CauseAbsences/add")
//	public ResponseEntity<HttpStatus> addCauseAbsence(@RequestBody @Valid CauseAbsence cause) {
//		absenceService.addCause(cause);
//		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
//	}
	
	@PutMapping("/CauseAbsence/update")
	public ResponseEntity<HttpStatus> updateCause(@RequestAttribute("auto_Id") Long idAuto,@RequestBody @Valid CauseAbsence cause) {
		cause.setId(cause.getId());
		absenceService.updateCauseAbsence(idAuto,cause);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("/CauseAbsence/delete")
	public ResponseEntity<HttpStatus> deleteCause(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
		absenceService.deleteCause(idAuto, request.getId());
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

		//**************************** SERVICES ROUTES ************************************************
		
		@PostMapping("/getAbsencesByIds")
		public ResponseEntity<List<Absence>> getAbsencesAutoEcoleByGroupOfids(@RequestAttribute("auto_Id") Long idAuto,@RequestBody List<Long> ids) {
			return new ResponseEntity<List<Absence>>(absenceService.getGroupOfAbsences(idAuto, ids),HttpStatus.OK);
		}
		
		@PostMapping("/getCausesByIds")
		public ResponseEntity<List<CauseAbsence>> getCausesAutoEcoleByGroupOfids(@RequestAttribute("auto_Id") Long idAuto,@RequestBody List<Long> ids) {
			return new ResponseEntity<List<CauseAbsence>>(absenceService.getGroupOfCauses(idAuto, ids),HttpStatus.OK);
		}
		
		@PostMapping("/getCauseAbsence")
		public ResponseEntity<CauseAbsence> getCauseAbsenceById(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {
			return new ResponseEntity<CauseAbsence>(absenceService.getCauseById(idAuto,request.getId()),HttpStatus.OK);
		}
		
		@GetMapping("/getAbsence")
		public ResponseEntity<Absence>getAbsenceById(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Request request) {	
			return new ResponseEntity<Absence>(absenceService.getAbsenceById(idAuto,request.getId()),HttpStatus.OK);
		}
}
