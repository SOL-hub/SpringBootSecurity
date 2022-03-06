package com.test.zaritalkParksol.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//사용자가 존재하지 않았을 때 500번 에러보다는 리소스가 존재하지 않은 것이기 때문에 notFound로
@ResponseStatus(HttpStatus.NOT_FOUND) //404번으로 클라이언트에게 전달
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}

}
