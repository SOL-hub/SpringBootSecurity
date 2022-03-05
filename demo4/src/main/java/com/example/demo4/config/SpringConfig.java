package com.example.demo4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo4.repository.MemoryUserRepository;
import com.example.demo4.repository.UserRepository;
import com.example.demo4.service.UserService;

@Configuration
public class SpringConfig {

	@Bean
	public UserService userService() {
		return new UserService(userRepository()); //로직을 호출해서 스프링빈에 등록
	} 
	
	@Bean
	public UserRepository userRepository() {
		return new MemoryUserRepository();
	}
}
