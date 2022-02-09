package com.sole.jwt.config.jwt;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	//login요청을 하면 로그인 시도를 위해서 실행되는 함수
	
	//1. username, password 받아서
	//2. 정상인지 로그인 시도를 해보는 것
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
		System.out.println("JwtAuthenticationFilter : 로그인 시도중");
		return super.attemptAuthentication(request, response);
	}

}
