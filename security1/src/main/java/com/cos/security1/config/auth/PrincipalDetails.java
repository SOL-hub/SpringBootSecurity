package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

import lombok.Data;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. (SEcurity ContextHloder)
//오브젝트 =>Authentication 타입 객체
//Authentication 안에 User정보가 있어야함.
//User오브젝트 타입 => UserDetails타입객체

//Security Session => authentication =>UserDetails

@Data
public class PrincipalDetails implements UserDetails{
	
	private User user; //콤포지션
	private Map<String, Object> attributes; 
	
	//일반로그인
	public PrincipalDetails(User user) {
		this.user=user;
	}

	//OAuth로그인
	public PrincipalDetails(User user, Map<String, Object>attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	//해당User의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect= new ArrayList<>();
		collect.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return user.getRole();
			}
			
		});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled(){
		//1년이상 회원 로그인 안하면 휴먼계정으로하기
		//현재시간-로긴시간 
		//user.getLoginDate();
		
		return true;
	}

//	@Override
//	public Map<String , Object> getAttributes(){
//		return attributes;
//	}
	
//	@Override
//	public String getName() {
//		return null;
//	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}