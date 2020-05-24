package com.blabz.fundoo.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blabz.fundoo.dto.ResponseDto;
import com.blabz.fundoo.dto.UserDto;
import com.blabz.fundoo.exception.UserException;
import com.blabz.fundoo.model.User;
import com.blabz.fundoo.service.IUserRegistrationService;
import com.blabz.fundoo.utility.Utility;

@RestController
@CrossOrigin("*")
public class RegistrationController {
	
	@Autowired
	IUserRegistrationService userService;
	
	@Autowired
	Utility utility;

	@PostMapping("/user")
	public ResponseEntity<ResponseDto> addUser(@Valid  @RequestBody UserDto userDto , BindingResult result) throws MessagingException {
		if(result.hasErrors()) {
			throw new UserException(UserException.ExceptionType.INVALID_DATA,"Invalid Data");
		}
		User user = userService.addUser(userDto);
		ResponseDto response = new ResponseDto("User Successfully Registered", user);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	@RequestMapping("/validateUser")
	public String validateUser( @RequestParam String token){
		String result = userService.verifyUser(token);
		System.out.println("result is--->"+result);
		return result;
	}
}
