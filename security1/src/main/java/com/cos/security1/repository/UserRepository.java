package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.security1.model.User;

//CRUE함수를 jpaRepository가 들고 있음
//@Repository라는 어노테이션이 없어도 Ioc된다. 이유는 JPaRepository가 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//findBy규칙 -> ㅕㄴㄷ구믇ansqjq
	//select * from user where username=1?
	public User findByUsername(String username); //JPA name함수
	
	public User findByEmail();
	
}
