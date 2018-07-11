package com.fundooNotes.exception;

public class ForgotPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ForgotPasswordException(String message) {
		super(message);
	}
}
