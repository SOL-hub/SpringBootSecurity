package com.example.demo4.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo4.domain.User;

public interface UserRepository {
	
	User save(User user); //저장
	Optional<User> findById(String id);//아이디 찾기
	Optional<User> findByusername(String username);
	
	List<User> findAll();
	
	void clearStore(); 

}
