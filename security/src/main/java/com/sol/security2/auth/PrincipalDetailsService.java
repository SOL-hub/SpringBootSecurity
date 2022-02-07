package com.sol.security2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sol.security2.model.User;
import com.sol.security2.repository.UserRepository;


//시큐리티 설정에서 loginProcessingUrl("/login"), login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		return null;
	}

}
