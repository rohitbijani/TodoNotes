package com.fundooNotes.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fundooNotes.exception.JwtException;
import com.fundooNotes.exception.NoteException;
import com.fundooNotes.exception.ForgotPasswordException;
import com.fundooNotes.exception.RegistrationException;
import com.fundooNotes.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExcpetion(Exception e){
		logger.error(e.getMessage(),e);
		return new ResponseEntity<>("Something went wrong \n", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(RuntimeException e) {
		logger.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<String> handleEmailNotUniqueException(RuntimeException e) {
		logger.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<String> handleJwtException(RuntimeException e) {
		logger.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ForgotPasswordException.class)
	public ResponseEntity<String> handleForgotPasswordException(RuntimeException e) {
		logger.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoteException.class)
	public ResponseEntity<String> handleNoteException(RuntimeException e) {
		logger.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
