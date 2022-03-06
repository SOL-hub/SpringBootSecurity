package com.test.zaritalkParksol.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.zaritalkParksol.domin.User;
import com.test.zaritalkParksol.service.UserService;

@RestController
public class UserController {

	public UserService userService;
	
	//의존성 주입
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//사용자 전체 목록 조회
	@GetMapping("/list")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	//상세조회를 하고 싶은 사용자 값
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable String id) {
		return userService.findOne(id);
	}
	
	@PostMapping("/users")
	public void join(@RequestBody User user) {
		User savedUser = userService.save(user);
	}
}
