package com.gsautoecole.serviceemployee.Exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
