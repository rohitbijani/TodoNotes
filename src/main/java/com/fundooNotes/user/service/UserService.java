package com.fundooNotes.user.service;

import com.fundooNotes.user.model.User;

import javax.servlet.http.HttpServletRequest;

import com.fundooNotes.user.model.LoginDto;
import com.fundooNotes.user.model.RegistrationDto;

public interface UserService {
	
	public Integer registerUser(RegistrationDto registrationDto, HttpServletRequest request);
	public User loginUser(LoginDto loginDto);
	public Integer verifyUser(String jwt);
	public Integer forgotPassword(String email, HttpServletRequest request);
	public Integer resetPassword(String jwt, String password);

}