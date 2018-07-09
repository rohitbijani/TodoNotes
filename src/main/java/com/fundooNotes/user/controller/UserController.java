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
	public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto) {
		String userInfo=userService.loginUser(loginDto);
		if(userInfo==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", userInfo);
		return new ResponseEntity<Void>(headers,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@Valid @RequestBody RegistrationDto registrationDto, HttpServletRequest request) {
		userService.registerUser(registrationDto,request);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/verification/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<Void> verify(@PathVariable("token") String token) {
		userService.verifyUser(token);
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/forgot-password/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<Void> forgotPassword(@PathVariable("email") String email, HttpServletRequest request) {
		userService.forgotPassword(email, request);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reset-password/{token:.+}", method = RequestMethod.POST)
	public ResponseEntity<Void> resetPassword(@PathVariable("token") String token, @RequestParam String password) {
		userService.resetPassword(token, password);
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
