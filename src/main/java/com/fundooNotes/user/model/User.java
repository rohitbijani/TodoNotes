package com.fundooNotes.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="User")
public class User {
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="name")
	@NotNull
	private String name;
	@Column(name="email", unique=true)
	@Email(message="Invalid email format")
	@Pattern(regexp="^([a-zA-Z0-9])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$")
	private String email;
	@Column(name="password")
	@Size(min=6)
	private String password;
	@Column(name="mobileNumber")
	@NotNull
	private String mobileNumber;
	@Column(name="verified")
	private boolean isVerified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
}
