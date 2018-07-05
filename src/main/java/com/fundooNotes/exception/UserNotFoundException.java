package com.fundooNotes.exception;

public class UserNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4018846490884906243L;

	public UserNotFoundException() {
		super("User not found!");
	}

}
