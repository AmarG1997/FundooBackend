package com.blabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blabz.fundoo.dto.LoginDto;
import com.blabz.fundoo.exception.UserException;
import com.blabz.fundoo.service.IUserRegistrationService;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	IUserRegistrationService userService;

	@PostMapping("/login")
	public ResponseEntity userLogin(@Valid @RequestBody LoginDto loginDto, BindingResult result) {
		if(result.hasErrors()) {
			throw new UserException(UserException.ExceptionType.INVALID_DATA, "Enter proper email and password");
		}
		String isLogin = userService.userLogin(loginDto);
		return new ResponseEntity(isLogin,HttpStatus.OK);
	}
	
	@PutMapping("/checkEmail")
	public String checkEmailIsPresent(@RequestBody LoginDto loginDto) {
		return userService.checkEmail(loginDto);
	}
}
