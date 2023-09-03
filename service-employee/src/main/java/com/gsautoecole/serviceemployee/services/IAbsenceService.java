package com.gsautoecole.serviceemployee.services;

import java.util.List;

import com.gsautoecole.serviceemployee.Entities.Absence;
import com.gsautoecole.serviceemployee.Entities.CauseAbsence;
import com.gsautoecole.serviceemployee.Entities.Employee;

public interface IAbsenceService {
	
	// for Absences
	public void addAbsence(Long idAuto,Absence abs);
		
	public void updateAbsence(Long idAuto,Absence abs);
	
	public void deleteAbsence(Long idAuto,Long id);
	
	public List<Absence> getAllAbsences(Long idAuto);

	public Absence getAbsenceById(Long idAuto,Long id);

	public Employee getAbsences(Long id) ;
	
	public List<Absence> getGroupOfAbsences(Long idAuto, List<Long> ids);
	
	// for causeAbsences
	public void addCause(Absence abs,String causeName);
	
	public void updateCauseAbsence(Long idAuto,CauseAbsence cause);
	
	public void deleteCause(Long idAuto,Long id);
	
	public List<CauseAbsence> getAllCauses(Long idAuto);

	public CauseAbsence getCauseById(Long idAuto,Long id);

	public List<CauseAbsence> getGroupOfCauses(Long idAuto, List<Long> ids);
}
