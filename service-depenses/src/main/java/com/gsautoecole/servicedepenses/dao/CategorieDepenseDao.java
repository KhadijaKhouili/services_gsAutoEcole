package com.gsautoecole.servicedepenses.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsautoecole.servicedepenses.entities.Categorie;
import com.gsautoecole.servicedepenses.enums.TypeDepense;

public interface CategorieDepenseDao extends JpaRepository<Categorie, Long>{
	
	public List<Categorie> findByTypeDep(TypeDepense typeDep);

	public List<Categorie> findByTypeDepAndAutoEcoleIdAndCategName(TypeDepense typeDep,Long idAuto,String name);

	public List<Categorie> findByAutoEcoleId(Long idAuto);
	
	
}
