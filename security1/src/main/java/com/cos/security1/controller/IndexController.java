package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller //view를 리턴하겠다.
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		//머스테치 기본폴더 src/main/resources/
		//뷰리졸버 설정 : templates(prefix), mustache(suffix)생략가능
		return "index"; 
	}
		
	@GetMapping("/user")
		public String user() {
			return  "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return  "admin";
	}	

	@GetMapping("manager")
	public String manager() {
		return "manager";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("join")
	public String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawpassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawpassword);
		user.setPassword(encPassword);
		userRepository.save(user);
		return "redirect:/loginForm";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	/*
	 * @GetMapping("joinProc") public @ResponseBody String joinProc() { return
	 * "회원가입완료"; }
	 */
	
	@PreAuthorize("hasRole('ROLE_MANAGER')or hasRole('ROLE_ADMIN')")
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
	
}

