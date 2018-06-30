package com.fundooNotes.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundooNotes.user.model.User;
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
		User userInfo=userService.loginUser(loginDto);
		if(userInfo==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestBody RegistrationDto registrationDto, HttpServletRequest request) {
		Integer id=userService.registerUser(registrationDto,request);
		if (id==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(value = "/verification/{token}", method = RequestMethod.GET)
	public void verify(@PathVariable("token") String token) {
		userService.verifyUser(token);
	}
}
