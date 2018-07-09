package com.fundooNotes.user.service;

import javax.servlet.http.HttpServletRequest;

import com.fundooNotes.user.model.LoginDto;
import com.fundooNotes.user.model.RegistrationDto;

public interface UserService {
	
	public void registerUser(RegistrationDto registrationDto, HttpServletRequest request);
	public String loginUser(LoginDto loginDto);
	public void verifyUser(String jwt);
	public void forgotPassword(String email, HttpServletRequest request);
	public void resetPassword(String jwt, String password);

}
