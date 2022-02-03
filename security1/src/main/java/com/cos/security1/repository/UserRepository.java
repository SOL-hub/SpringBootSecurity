package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.security1.model.User;

//CRUE함수를 jpaRepository가 들고 있음
//@Repository라는 어노테이션이 없어도 Ioc된다. 이유는 JPaRepository가 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
	//회원가입을 강제로 진행해볼 에정
	//String provider = userRequest.getClientRegistration().getClientId(); //google
	//String providerId = oauth2User.getAttribute("sub");
	//String username = provider+"-"+providerId; 
	//String password = BCryptPasswordEncoder.encode("겟인데어");
	//String email = oauth2User.getAttribute("email");
	//String role = "ROLE_USER";
	
//	User userEntity = userRepository.findByUsername(username);
//	
//	if(userEntity == null) {
//		
//	}else {
		
//	}
	
//	return super.loadUser(userRequest);
}
