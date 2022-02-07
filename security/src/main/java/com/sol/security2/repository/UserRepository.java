package com.sol.security2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sol.security2.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	//public User findByEmail();
}
