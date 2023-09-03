package com.gsautoecole.serviceemployee.services;

import com.gsautoecole.serviceemployee.Entities.Employee;
import com.gsautoecole.serviceemployee.Entities.Moniteur;
import com.gsautoecole.serviceemployee.Entities.TypeEmploie;

public interface ItypeEmploieService {
	
	public void addTypeEmploie(Employee e,String type);
	
	public void addTypeEmp(TypeEmploie type);
	
	public void apdateTypeEmploie(TypeEmploie type);

	void addTypeEmploie(Moniteur monit, String type);
}
