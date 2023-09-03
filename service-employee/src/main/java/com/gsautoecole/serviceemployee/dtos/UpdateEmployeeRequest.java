package com.gsautoecole.serviceemployee.dtos;

import com.gsautoecole.serviceemployee.Entities.Employee;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UpdateEmployeeRequest {
	
	private Long id;
	private Employee employee;
}
