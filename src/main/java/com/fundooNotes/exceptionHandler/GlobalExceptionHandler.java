package com.fundooNotes.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fundooNotes.exception.JwtException;
import com.fundooNotes.exception.NoteException;
import com.fundooNotes.exception.ForgotPasswordException;
import com.fundooNotes.exception.RegistrationException;
import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.user.model.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@Autowired
	Response response;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleExcpetion(Exception e){
		logger.error(e.getMessage(),e);
		response.setMessage("Something went wrong");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> handleUserNotFoundException(RuntimeException e) {
		logger.info(e.getMessage());
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<Response> handleEmailNotUniqueException(RuntimeException e) {
		logger.info(e.getMessage());
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<Response> handleJwtException(RuntimeException e) {
		logger.info(e.getMessage());
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(ForgotPasswordException.class)
	public ResponseEntity<Response> handleForgotPasswordException(RuntimeException e) {
		logger.info(e.getMessage());
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoteException.class)
	public ResponseEntity<Response> handleNoteException(RuntimeException e) {
		logger.info(e.getMessage());
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
}
