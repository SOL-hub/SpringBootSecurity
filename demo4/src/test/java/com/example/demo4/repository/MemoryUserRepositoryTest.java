package com.example.demo4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.demo4.domain.User;

public class MemoryUserRepositoryTest {
	
	UserRepository repository = new MemoryUserRepository();

	//테스트가 하나 끝나고나면 클리어를 해주어야한다.
	@AfterEach //메서드의 실행이 끝날 때 마다 실행시키는 콜백 매소드
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		User user = new User();
		user.setUsername("임차인_김씨");
		
		repository.save(user);
		
		User result = repository.findById(user.getId()).get();
		Assertions.assertThat(user).isEqualTo(result);
	}
	
	@Test
	public void findByUsername() {
		User user1 = new User();
		user1.setUsername("임대인_박씨");
		repository.save(user1); 
		
		User user2 = new User();
		user2.setUsername("공인중개사_양씨");
		repository.save(user2);
		
		User result = repository.findByusername("공인중개사_양씨").get();
		
		assertThat(result).isEqualTo(user2);
	}
	 
	@Test
	public void findAll() {
		User user1 = new User();
		user1.setUsername("임대인_박씨");
		repository.save(user1); 
		
		User user2 = new User();
		user2.setUsername("공인중개사_양씨");
		repository.save(user2);
		
		List<User> result = repository.findAll();
		
		//저장되어있는 데이터가 몇개 있는지 테스트
		assertThat(result.size()).isEqualTo(1); 
	}
	 
}
