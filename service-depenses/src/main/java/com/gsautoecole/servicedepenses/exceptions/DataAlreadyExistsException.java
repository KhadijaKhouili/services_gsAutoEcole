package com.gsautoecole.servicedepenses.exceptions;

public class DataAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAlreadyExistsException(String message) {
        super(message);
    }
}
