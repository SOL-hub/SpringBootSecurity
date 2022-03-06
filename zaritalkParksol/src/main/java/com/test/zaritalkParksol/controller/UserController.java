package com.test.zaritalkParksol.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		User user = userService.findOne(id);
		
		if(user == null) {
			//throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> join(@RequestBody User user) {
		User savedUser = userService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
