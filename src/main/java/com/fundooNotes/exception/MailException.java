package com.fundooNotes.exception;

public class MailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MailException(String message) {
		super(message);
	}
}
