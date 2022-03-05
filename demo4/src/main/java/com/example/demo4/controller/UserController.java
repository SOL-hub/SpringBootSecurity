package com.example.demo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo4.domain.User;
import com.example.demo4.service.UserService;

@Controller
public class UserController {

	//user서비스를 가져와야한다.
	//스프링관리 시 container에 등록하고 받아쓰도록해야한다. 
	//new 해서 생성한다면 다른 컨트롤러들이 userService를 가져다 쓸 수 있기 때문에 생성자 만들어서 구현하기
	private final UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/write")
	public String write() throws Exception{
		return "write";
	}
	
	@GetMapping(value = "/join")
	public String join() {
		return "join";
	}
}
