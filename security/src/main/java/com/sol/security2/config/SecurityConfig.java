package com.sol.security2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sol.security2.auth.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private PrincipalOauth2UserService prinOauth2UserService;
	
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()
		.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")//이 권한이 있는 사람만 들어올 수 있게한다.
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm") 
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/")
		.and()
		.oauth2Login()
		.loginPage("/loginForm")
		.userInfoEndpoint()
		.userService(prinOauth2UserService);
	}
}
