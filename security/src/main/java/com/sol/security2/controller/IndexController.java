package com.sol.security2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sol.security2.auth.PrincipalDetails;
import com.sol.security2.model.User;
import com.sol.security2.repository.UserRepository;

@Controller
public class IndexController {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrpBCryptPasswordEncoder;
	
	

	@GetMapping("/test/login")
	public @ResponseBody String testLogin(Authentication authentication,
			@AuthenticationPrincipal PrincipalDetails userDetails) { //DI 의존성 주입
		System.out.println("/test/login=========");
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("authentication:"+principalDetails.getUser());
		
		System.out.println("userDetails:"+userDetails.getUser());
		return "세션 정보 확인하기";
	}
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody String testOAuthLogin(
			Authentication authentication, @AuthenticationPrincipal OAuth2User oauth){ //DI 의존성 주입
		System.out.println("/test/login=========");
		OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("authentication:"+oauth2User.getAttributes());
		
		System.out.println("oauth2User:"+oauth.getAttributes());
		return "OAuth 세션 정보 확인하기";
	}
	
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
	
	@Secured("ROLE_ADMIN")
	@GetMapping("info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	//여러개를 하려할 경우 hasRole넣어서 작성
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")//데이터가 실행되기 직전에 실행된다.
	@GetMapping("data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
	
	
}
