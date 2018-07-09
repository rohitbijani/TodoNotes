package com.fundooNotes.user.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegistrationDto {
	@NotBlank
	private String name;
	@Email
//	@Pattern(regexp="^([a-zA-Z0-9])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$")
	private String email;
	@Size(min=6)
	private String password;
	@NotNull @Size(min=10)
	private String mobileNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
