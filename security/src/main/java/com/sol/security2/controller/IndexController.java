package com.sol.security2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sol.security2.model.User;
import com.sol.security2.repository.UserRepository;

@Controller
public class IndexController {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrpBCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		//머스테치 기본폴더 src/main/resources/
		//뷰리졸버 설정 : templates(prefix), mustache(suffix)생략가능
		return "index"; 
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		System.out.println("user"+user);
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String endcPassword = bCrpBCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(endcPassword);
		userRepository.save(user); 
		return "redireact:/loginForm";
	}
	
}
