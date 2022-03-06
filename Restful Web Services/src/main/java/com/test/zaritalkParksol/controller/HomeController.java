package com.test.zaritalkParksol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.zaritalkParksol.exception.UserList;

@RestController//view가 아닌 Rest Data(JSON)를 반환하기 위해 @Controller가 아닌 @RestController 사용
public class HomeController {
	
	@GetMapping(path="/home")
	public String home() {
		return "home";
	}
	
	//@GetMapping(path="/list")
	//public UserList list() {
	//	return new UserList("list");
	//}
	
	//@GetMapping(path="/list/path-variable/{name}")
	//public UserList list(@PathVariable String name) {
	//	return new UserList(String.format("List, %s", name));
	//}
	
}
