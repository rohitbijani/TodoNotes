package com.fundooNotes.user.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class LoginDto {
	@Email
	private String email;
	@Size(min=6)
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
