package com.fundooNotes.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fundooNotes.exception.RegistrationException;
import com.fundooNotes.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFoundException(RuntimeException e) {
		logger.info("UserNotFoundException Occured");
		return e.getMessage();
	}
	
	@ExceptionHandler(RegistrationException.class)
	public String handleEmailNotUniqueException(RuntimeException e) {
		logger.info("EmailNotUniqueException Occured");
		return e.getMessage();
	}
}
