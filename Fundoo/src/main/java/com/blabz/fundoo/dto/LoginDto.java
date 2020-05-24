package com.blabz.fundoo.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

public class LoginDto {

	@NotNull
	@NotEmpty
	@Length(min = 2, max = 50, message = "Invalid Data")
	public String email;
	@NotNull
	@Length(min = 8, max = 50, message = "Invalid Data")
	@NotEmpty
	public String password;
	
}
