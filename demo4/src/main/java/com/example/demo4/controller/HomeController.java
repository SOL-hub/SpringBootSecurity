package com.example.demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//목록조회
	@GetMapping(value = "/list")
	public String list() {
		return "/list";
	}
	
	/*
	 * //회원가입
	 * 
	 * @GetMapping(value = "/join") public String join() { return "join"; }
	 * 
	 * //회원가입
	 * 
	 * @PostMapping(value = "/joinFinish") public String joinFinish() { return
	 * "joinFinish"; }
	 * 
	 * //회원 목록 조회
	 * 
	 * @GetMapping(value = "/List") public String List() { return "List"; }
	 * 
	 * //글쓰기
	 * 
	 * @GetMapping(value = "/write") public String write() { return "write"; }
	 */
}
