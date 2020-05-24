package com.blabz.fundoo.service;

import javax.mail.MessagingException;

import com.blabz.fundoo.dto.LoginDto;
import com.blabz.fundoo.dto.UserDto;
import com.blabz.fundoo.model.User;

public interface IUserRegistrationService {	
	User addUser(UserDto userDto) throws MessagingException;
	String userLogin(LoginDto loginDto);
	String checkEmail(LoginDto loginDto);
	String verifyUser(String token);
}
