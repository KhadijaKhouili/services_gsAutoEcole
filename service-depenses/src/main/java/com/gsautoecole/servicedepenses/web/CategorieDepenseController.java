package com.gsautoecole.servicedepenses.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsautoecole.servicedepenses.entities.Categorie;
import com.gsautoecole.servicedepenses.enums.TypeDepense;
import com.gsautoecole.servicedepenses.services.ICategoriDepenseService;

@RestController
@RequestMapping("/depenses")
public class CategorieDepenseController {
	
	@Autowired
	private ICategoriDepenseService cateService;

	
	@GetMapping("/CategorieDep")
	public ResponseEntity<List<Categorie>> getAllCategoriesAuto(@RequestAttribute("auto_Id") Long idAuto) {
		return new ResponseEntity<List<Categorie>>(cateService.getAllCategoriesDepByAuto(idAuto), HttpStatus.OK) ;
	}
	
	@GetMapping("/CategorieDepByTypeDep")
	public ResponseEntity<List<Categorie>> getAllCategoriesPersonnelAuto(@RequestAttribute("auto_Id") Long idAuto,@RequestParam TypeDepense type) {
		return new ResponseEntity<List<Categorie>>(cateService.getAllCategoriesDep(idAuto, type),HttpStatus.OK);
	}
	
	@PostMapping("/CategorieDep/add")
	public ResponseEntity<HttpStatus> addCategorieAuto(@RequestAttribute("auto_Id") Long idAuto,@RequestBody Categorie categorie) {
		cateService.addCategorieDepAutoEcole(idAuto, categorie.getTypeDep().name(), categorie);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@PutMapping("/CategorieDep/update")
	public ResponseEntity<Categorie> updateCategorieAuto(@RequestAttribute("auto_Id") Long idAuto, @RequestBody Categorie categorie) {
		return new ResponseEntity<Categorie>(cateService.updateCategorieAuto(idAuto, categorie.getId(), categorie),HttpStatus.OK);
	}
	
}
