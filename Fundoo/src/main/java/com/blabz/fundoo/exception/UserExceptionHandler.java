package com.blabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(value = UserException.class)
	public ResponseEntity <Object>userExceptionHandler(UserException userException) {
		return new ResponseEntity(userException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Object> nullPointerException(UserException userException){
		return new ResponseEntity("Null pointer exception", HttpStatus.BAD_REQUEST);
	}

}
