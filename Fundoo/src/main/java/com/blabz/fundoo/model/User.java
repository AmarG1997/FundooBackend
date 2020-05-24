package com.blabz.fundoo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.blabz.fundoo.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;
	public boolean isVerify;
	
	public User( UserDto user) {
		this.firstName=user.firstName;
		this.lastName=user.lastName;
		this.email=user.email;
		this.password=user.password;
		this.confirmPassword=user.confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
//	public boolean isVerify() {
//		return isVerify;
//	}

	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	public User() {
//		super();
	}
	
	
}
