package com.blabz.fundoo.exception;

public class UserException extends RuntimeException {
	
	public ExceptionType type;
	
	public enum ExceptionType{
		INVALID_DATA,NULL_POINTER_EXCEPTION
	}
	
	public UserException(ExceptionType type , String message) {
		super(message);
        this.type = type;
	}
	
	
}
