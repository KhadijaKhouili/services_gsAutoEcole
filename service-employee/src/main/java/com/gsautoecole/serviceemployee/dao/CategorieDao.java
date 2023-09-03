package com.gsautoecole.serviceemployee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsautoecole.serviceemployee.Entities.CategorieMonit;
import com.gsautoecole.serviceemployee.enums.TypeCategorie;

public interface CategorieDao extends JpaRepository<CategorieMonit,Long>{

    CategorieMonit findByMoniteursAutoEcoleIdAndLibelle(Long idAuto, TypeCategorie type);

}
