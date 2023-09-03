package com.gsautoecole.serviceemployee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.gsautoecole.serviceemployee.Entities.Absence;
import com.gsautoecole.serviceemployee.Entities.CauseAbsence;
import com.gsautoecole.serviceemployee.Entities.Employee;
import com.gsautoecole.serviceemployee.dao.AbsenceDao;
import com.gsautoecole.serviceemployee.dao.CauseAbsenceDao;
import com.gsautoecole.serviceemployee.dao.EmployeeDao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AbsenceServiceImp implements IAbsenceService{
    
	private AbsenceDao absencedao;
	private CauseAbsenceDao causedao;
	private EmployeeDao empdao;
	
	public AbsenceServiceImp(AbsenceDao dao, CauseAbsenceDao cause,EmployeeDao empdao) {
		this.absencedao = dao;
		this.causedao = cause;
		this.empdao = empdao;
	}
	
	@Override
	public void addAbsence(Long idAuto,Absence abs) {
        Employee emp = empdao.findByAutoEcoleIdAndId(idAuto, abs.getEmp().getId());
		if(emp.getAutoEcoleId()==idAuto) {
			absencedao.save(abs);
		}else {
			throw new EntityNotFoundException("Cet employe n'appartient pas a cette auto-ecole !");
		}
		
		
	}

	@Override

	public void updateAbsence(Long idAuto,Absence NvAbsence) {
		CauseAbsence c = causedao.findByCause(NvAbsence.getCauseAbsence().getCause());
		if(c != null) {
			NvAbsence.setCauseAbsence(c);
		}else {
			c = new CauseAbsence();
			c.setCause(NvAbsence.getCauseAbsence().getCause());
			causedao.save(c);
			NvAbsence.setCauseAbsence(causedao.findByCause(NvAbsence.getCauseAbsence().getCause()));
		}
		
		absencedao.save(NvAbsence);
	
	}
	
	@Override
	public void deleteAbsence(Long idAuto,Long id) {
		Absence absence = absencedao.findByEmpAutoEcoleIdAndId(idAuto, id);
		if(absence != null) {
			absence.getEmp().removeAbsence(absence);
			absencedao.delete(absence);
		}else{
			throw new EntityNotFoundException("Cet absence n'a pas été trouvé");
		}
		
		
	}

	@Override
	public List<Absence> getAllAbsences(Long idAuto) {
		return absencedao.findByEmpAutoEcoleId(idAuto);
		
	}

	@Override
	public Absence getAbsenceById(Long idAuto,Long id) {
		return absencedao.findByEmpAutoEcoleIdAndId(idAuto, id);
	}

	@Override
	public List<Absence> getGroupOfAbsences(Long idAuto, List<Long> ids) {
		List<Absence> absences = new ArrayList<>();
		ids.forEach(id -> {
			Absence abs = absencedao.findByEmpAutoEcoleIdAndId(idAuto, id);
			if(abs != null) {
				absences.add(abs);
			}	
		});
		if(absences.size() == ids.size()) {
			return absences;
		}else {
			throw new EntityNotFoundException("Les absences n'a pas été tous trouvé !");
		}

	}
    
	//*************************************** Functions of Cause class **********************************


	@Override
	public void addCause(Absence abs,String causeName) {
		CauseAbsence cause = causedao.findByCause(causeName);
		//Si la cause n'existe pas
		if(cause == null) {
			System.out.println("cause not exist : ");
			cause = new CauseAbsence();
			cause.setCause(causeName);
			causedao.save(cause);
		}
			// Associer la cause à l'absence
		    cause.getAbsences().add(abs);
			abs.setCauseAbsence(cause);		
	}
	@Override
	public void deleteCause(Long idAuto,Long id) {
		CauseAbsence c = causedao.findByAbsencesEmpAutoEcoleIdAndId(idAuto,id);
		if(c != null) {
			List<Absence> absences = c.getAbsences();
			if(!absences.isEmpty()){
				System.out.println(absences);
				for (Absence absence : absences) {
					deleteAbsence(idAuto,absence.getId());
			    }
			}
			causedao.deleteById(id);
		}else{
			throw new EntityNotFoundException("Cet cause n'a pas été trouvé");
		}
		
	}

	@Override
	public List<CauseAbsence> getAllCauses(Long idAuto) {
		List<CauseAbsence> causes = causedao.findByAbsencesEmpAutoEcoleId(idAuto);
		if(causes.isEmpty()){
			throw new EntityNotFoundException("Cette cause n'a pas été trouvé");
		}else{
			return causes;
		}
		
	}

	@Override
	public CauseAbsence getCauseById(Long idAuto,Long id) {
		CauseAbsence cause = causedao.findByAbsencesEmpAutoEcoleIdAndId(idAuto, id);
		if(cause != null){
			return causedao.findByAbsencesEmpAutoEcoleIdAndId(idAuto, id);
		}else{
			throw new EntityNotFoundException("Cette cause n'a pas été trouvé");
		}
		
	}

	@Override
	public void updateCauseAbsence(Long idAuto,CauseAbsence cause) {
		CauseAbsence c = causedao.findByAbsencesEmpAutoEcoleIdAndId(idAuto,cause.getId());
		if(c != null) {
			causedao.save(cause);
		}else{
			throw new EntityNotFoundException("Cette cause n'a pas été trouvé");
		}
		
	}

	@Override
	public List<CauseAbsence> getGroupOfCauses(Long idAuto, List<Long> ids) {
		List<CauseAbsence> causes = new ArrayList<>();
		ids.forEach(id -> {
			CauseAbsence cause = causedao.findByAbsencesEmpAutoEcoleIdAndId(idAuto, id);
			if(cause != null) {
				causes.add(cause);
			}	
		});
		if(causes.size() == ids.size()) {
			return causes;
		}else {
			throw new EntityNotFoundException("Les causes n'a pas été tous trouvé !");
		}

	}

    @Override
    public Employee getAbsences(Long id) {
		return absencedao.findById(id).get().getEmp();
	}
}
