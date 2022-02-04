package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;

@EnableWebSecurity //스프링 시큐리티 필터가 스프링필터 체인에 등록이된다.
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)//secured 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalOauth2UserService principalDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //비활성화하고
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated() //인증만 되면 들어갈 수 있는 주소
		.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")//요 권한이 있는 사람만 들어가게될 것이다. 
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/login")//login주소가 호출이되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
		.defaultSuccessUrl("/")
		.and()
		.oauth2Login()
		.loginPage("/loginForm") //구글 로그인이 완료된 뒤의 후처리가 필요함 1.코드받기(인증), 2. 엑세스토큰(권한), 3. 사용자 프로필 정보를 가져오고 , 4.그 외 정보를 토대로 회원가입을 자동으로 진행시키기도함
		.userInfoEndpoint()
		.userService(principalDetailsService);
	}

}
