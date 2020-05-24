package com.blabz.fundoo.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

public class UserDto {
	
	@NotNull
	@NotEmpty
	@Length(min = 2 , max = 15 ,message = "Invalid first name")
	public String firstName;
	@NotNull
	@NotEmpty
	@Length(min = 2 , max = 15 ,message = "Invalid first name")
	public String lastName;
	@NotNull
	@NotEmpty
	public String email;
	@NotNull
	@NotEmpty
	@Length(min = 8 , max = 30 ,message = "Invalid first name")
	public String password;
	@NotNull
	@NotEmpty
	public String confirmPassword;
	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", confirmPassword=" + confirmPassword + "]";
	}
		
	
}