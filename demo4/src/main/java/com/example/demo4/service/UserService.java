package com.example.demo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo4.domain.User;
import com.example.demo4.repository.MemoryUserRepository;
import com.example.demo4.repository.UserRepository;

//@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	//직접내가 생성하는 것이 아니라 외부에서 넣어줄 수 있도록 바꿔주기.
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//회원 가입
	public String join(User user) {
		
		//중복회원X 검증.
		sameUserOut(user);
		
		userRepository.save(user);
		return user.getId();
	}
	
	private void sameUserOut(User user) {
		userRepository.findById(user.getId()).ifPresent(u -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	//회원 조회
	public List<User> findUsers(){
		return userRepository.findAll();
		
	}
	
	//단일 조회
	public Optional<User> findOne(String userId){
		return userRepository.findById(userId);
	}
}
