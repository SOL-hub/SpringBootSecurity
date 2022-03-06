package com.test.zaritalkParksol.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.test.zaritalkParksol.domin.User;
import com.test.zaritalkParksol.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	public UserService userService;
	
	//의존성 주입
	public AdminController(UserService userService) {
		this.userService = userService;
	}
	
	//사용자 전체 목록 조회
	@GetMapping("/users")
	public MappingJacksonValue findAll(){
		List<User> users = userService.findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "nickname", "quit", "joinDate");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	//상세조회를 하고 싶은 사용자 값
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable String id) {
		User user = userService.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "nickname", "quit", "joinDate");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return user;
	}
	

	
}
