package com.blabz.fundoo.dto;

import com.blabz.fundoo.model.User;

public class ResponseDto {
	String message;
    User object;

    public ResponseDto(String msg, User obj) {
        this.message = msg;
        this.object = obj;
    }

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", object=" + object + "]";
	}

	public ResponseDto() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getObject() {
		return object;
	}

	public void setObject(User object) {
		this.object = object;
	}
	
	
	
}
