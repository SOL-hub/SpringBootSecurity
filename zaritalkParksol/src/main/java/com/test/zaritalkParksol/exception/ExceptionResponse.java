package com.test.zaritalkParksol.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//예외처리 AOP기능 사용

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ExceptionResponse {
	
	private Date timestamp; //예외가 발생한 시간에 대한 정보
	private String message; //예외가 발생한 메세지
	private String details;//예외의 상세정보
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	
	
}
