package com.gsautoecole.serviceemployee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsautoecole.serviceemployee.Entities.CauseAbsence;

public interface CauseAbsenceDao extends JpaRepository<CauseAbsence, Long>{
	
	public CauseAbsence findByCause(String causeName);

    public CauseAbsence findByAbsencesEmpAutoEcoleIdAndId(Long idAuto, Long id);

    public List<CauseAbsence> findByAbsencesEmpAutoEcoleId(Long idAuto);
}
