package com.gsautoecole.serviceemployee.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsautoecole.serviceemployee.Entities.Absence;
public interface AbsenceDao extends JpaRepository<Absence, Long>{

    List<Absence> findByEmpAutoEcoleId(Long idAuto);

    Absence findByEmpAutoEcoleIdAndId(Long idAuto, Long id);
	
}
