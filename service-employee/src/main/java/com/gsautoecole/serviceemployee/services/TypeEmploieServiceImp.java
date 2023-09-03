package com.gsautoecole.serviceemployee.services;

import org.springframework.stereotype.Service;

import com.gsautoecole.serviceemployee.Entities.Employee;
import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.Entities.TypeEmploie;
import com.gsautoecole.serviceemployee.dao.EmployeeDao;
import com.gsautoecole.serviceemployee.dao.TypeEmploieDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TypeEmploieServiceImp implements ItypeEmploieService{

	private TypeEmploieDao TempDao;
	private EmployeeDao empdao;
	
	public TypeEmploieServiceImp(TypeEmploieDao TempDao,EmployeeDao emp) {
		this.TempDao = TempDao;
		this.empdao = emp;
	}
	
	@Override
	public void addTypeEmploie(Employee emp,String type) {
		TypeEmploie t = TempDao.findByTpemploie(type);
		if(t != null) {
			System.out.println("type emp existe : "+t);
			emp.setTypeEmp(t);
		}else {
			TempDao.save(new TypeEmploie(null, type));
			emp.setTypeEmp(TempDao.findByTpemploie(type));
		}
		
	}
	@Override
	public void addTypeEmploie(Moniteur monit,String type) {
		TypeEmploie t = TempDao.findByTpemploie(type);
		if(t != null) {
			System.out.println("type emp existe : "+t);
			monit.setTypeEmp(t);
		}else {
			TempDao.save(new TypeEmploie(null, type));
			monit.setTypeEmp(TempDao.findByTpemploie(type));
		}
		
	}

	@Override
	public void apdateTypeEmploie(TypeEmploie type) {
		
	}

	@Override
	public void addTypeEmp(TypeEmploie type) {
		TempDao.save(type);
	}

}
