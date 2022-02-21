package com.sole.security;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.security.PermitAll;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
		auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS", "USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN", "SYS", "USER");
		
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/admin/pay").hasRole("ADMIN")
		//.antMatchers("/admin/**").access("hasRole('ADMIN')or hasRole('SYS')")
		.anyRequest().authenticated();
		
		http
		.formLogin()
		.successHandler(new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				RequestCache requestCache = new HttpSessionRequestCache();
				org.springframework.security.web.savedrequest.SavedRequest savedRequest = requestCache.getRequest(request, response);
				String redirectUrl = savedRequest.getRedirectUrl();
				response.sendRedirect(redirectUrl);
				
				
			}
		});
		
		http
			.exceptionHandling()
			.authenticationEntryPoint(new AuthenticationEntryPoint() {
				
				@Override
				public void commence(HttpServletRequest request, HttpServletResponse response,
						org.springframework.security.core.AuthenticationException authException)
						throws IOException, ServletException {
						
					response.sendRedirect("/login");
				}
			})
			
		.accessDeniedHandler(new AccessDeniedHandler() {
			
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.sendRedirect("/denied");
				
			}
		});
		
		
		}
	}
