package com.sole.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class securityController {

	@GetMapping("/")
	public String index() {
		return "home";
	}
	
	//@GetMapping("loginPage")
	//public String loginPage() {
	//	return "loginPage";
	//}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin/Pay")
	public String adminPay() {
		return "adminPay";
	}
	
	@GetMapping("/admin/**")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/login")
	public String login() {
		return "Access is denied";
	}
	
	
	
	
}
