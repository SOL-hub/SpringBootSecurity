package com.example.demo4.config;

import java.security.cert.Extension;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//사용자 추가
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//패스워드는 암호화된 방식으로 주어야 오류예방
	//	String password = passwordEncoder().encode("1111");
		
	/*
	 * auth.inMemoryAuthentication().withUser("user").roles("USER");
	 * auth.inMemoryAuthentication().withUser("manager").roles("MANAGER");
	 * auth.inMemoryAuthentication().withUser("admin").roles("ADMIN");
	 */
		
		/*
		 * auth.inMemoryAuthentication().withUser("user").password(password).roles(
		 * "USER");
		 * auth.inMemoryAuthentication().withUser("manager").password(password).roles(
		 * "MANAGER");
		 * auth.inMemoryAuthentication().withUser("admin").password(password).roles(
		 * "ADMIN");
		 */
	}

	/*
	 * @Bean //패스워드 암호화 private PasswordEncoder passwordEncoder() { return
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		
			.authorizeRequests()
			.antMatchers("/", "/join**", "/list").permitAll()//인증, 권한없어도 가능하도록, 첫 화면or로그인 후or 목록조회 시 접근가능
			//.antMatchers("user/write/**").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER)")//이 권한이 있는 사람만 들어올 수 있게한다. (글쓰기는 비회원은 접근 불가)
			.antMatchers("/write/**").hasRole("USER")//이 권한이 있는 사람만 들어올 수 있게한다. (글쓰기는 비회원은 접근 불가)
			.antMatchers("user/config").hasRole("ADMIN") //관리 구역
			
			.anyRequest().authenticated()
			.and().formLogin();
	}
}
