package com.fundooNotes.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundooNotes.user.model.LoginDto;
import com.fundooNotes.user.model.RegistrationDto;
import com.fundooNotes.user.service.UserService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
		String loginJwt=userService.loginUser(loginDto);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", loginJwt);
		return new ResponseEntity<>("Login successful", headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<String> register(@Valid @RequestBody RegistrationDto registrationDto, HttpServletRequest request) {
		userService.registerUser(registrationDto,request);
		
		return new ResponseEntity<>("Registration done. Check your mail", HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/verification/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> verify(@PathVariable("token") String token) {
		userService.verifyUser(token);
		
		return new ResponseEntity<>("Account verified", HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/forgot-password/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<String> forgotPassword(@PathVariable("email") String email, HttpServletRequest request) {
		userService.forgotPassword(email, request);
		
		return new ResponseEntity<>("Mail sent", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reset-password/{token:.+}", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@PathVariable("token") String token, @RequestParam String password) {
		userService.resetPassword(token, password);
		
		return new ResponseEntity<>("Password changed successfully", HttpStatus.ACCEPTED);
	}
	
}
