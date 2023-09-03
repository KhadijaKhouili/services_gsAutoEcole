package com.gsautoecole.servicedepenses.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.gsautoecole.servicedepenses.entities.Depense;
import com.gsautoecole.servicedepenses.enums.TypeDepense;

public interface IDepenseDao extends JpaRepository<Depense, Long>{
	
	public List<Depense> findByAutoEcoleId(@Param("idAuto") Long idAuto);
	
	public Depense findByAutoEcoleIdAndId(@Param("idAuto") Long idAuto,@Param("idDep") Long idDep);
	
	public List<Depense> findByAutoEcoleIdAndCategorieTypeDepAndDateDepBetween(Long idAuto,TypeDepense type_dep, LocalDate startDate, LocalDate endDate);
	
	public List<Depense> findByAutoEcoleIdAndDateDepBetween(Long idAuto,LocalDate startDate, LocalDate endDate);
	public List<Depense> findByAutoEcoleIdAndEmpId(Long idAuto,Long idEmp);
	
	public List<Depense> findByAutoEcoleIdAndVehiculeId(Long idAuto,Long idVeh);

	
	
	
//	public List<Depense> findByAuto_ecoleId(Long idAuto);
//	public Depense findByAuto_ecoleIdAndId(Long idAuto,Long idDep);
//	public List<Depense> findByAuto_ecoleIdAndType_depenseAndDate_depBetween(Long idAuto,TypeDepense type_dep, LocalDate startDate, LocalDate endDate);
//	public List<Depense> findByAuto_ecoleIdAndDate_depBetween(Long idAuto,LocalDate startDate, LocalDate endDate);
}
