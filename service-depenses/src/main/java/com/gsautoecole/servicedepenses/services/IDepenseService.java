package com.gsautoecole.servicedepenses.services;

import java.time.LocalDate;
import java.util.List;

import com.gsautoecole.servicedepenses.dtos.DepenseDTO;
import com.gsautoecole.servicedepenses.dtos.DepenseResponse;
import com.gsautoecole.servicedepenses.entities.Depense;
import com.gsautoecole.servicedepenses.enums.TypeDepense;

public interface IDepenseService {
	
	public DepenseResponse getDepenseByid(Long idAuto,Long id);

	//public Depense getDepenseByAutoEcole(Long idAuto, Long idDep);
	
	public List<DepenseResponse> getAllDepensesByAutoEcole(Long idAuto);
	
	public Depense addDepenseAutoEcole(Long idAuto,Depense depense);
	
	public Depense updateDepenseAutoEcole(Long idAuto,Depense depense,Long idDep);
	
	public void deleteDepenseAutoEcole(Long idAuto,Long idDep);

	public List<DepenseResponse> getAllDepensesByAutoEcoleAndTypeCat(Long idAuto,TypeDepense type);

	public DepenseDTO getAllDepenseAutoEcoleBetweenDates(Long idAuto, TypeDepense type, LocalDate datedebut,LocalDate dateFin);

	public List<Depense> getAllDepensesByAutoEcoleAndEmp(Long idAuto,Long idEmp);

	public List<Depense> getAllDepensesByAutoEcoleAndVehicule(Long idAuto, Long idVeh);

	public List<DepenseResponse> getGroupOfDepenses(Long idAuto, List<Long> ids);
	
}
