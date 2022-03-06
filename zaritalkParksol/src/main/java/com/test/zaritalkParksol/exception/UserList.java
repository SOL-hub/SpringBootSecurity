package com.test.zaritalkParksol.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class UserList {
	private String message;
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String msg) {
		this.message=msg;
	}

	public UserList(String message) {
		super();
		this.message = message;
	}

}
