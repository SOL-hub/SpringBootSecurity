package com.example.demo4.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo4.domain.User;
import com.example.demo4.repository.MemoryUserRepository;

public class UserServiceTest {
	
	//서비스를 가져온다.
	UserService userService;
	
	//클리어를 위해 리포지토리를 가져온다.(@AfterEach사용하기 위해)
	MemoryUserRepository userRepository;
	
	@BeforeEach
	public void beforeEach() {
		
		//테스트를 실행할 때마다 각 생성을해서 각각의 리포지토리, 서비스를 사용하게 만들기 DI 
		userRepository = new MemoryUserRepository();
		userService = new UserService(userRepository);
		
	}
	
	@AfterEach
	public void afterEach() {
		userRepository.clearStore();
	}
	
	//회원가입이 잘 되었나 확인
	@Test
	void join() {
		
		User user = new User();
		user.setId("test1");
		
		String saveId = userService.join(user);
		
		//검증- repository(저장소)에 꺼낸게 맞아? 를 검증
		User findUser = userService.findOne(saveId).get();
		Assertions.assertThat(user.getUsername()).isEqualTo(findUser.getId());
		 
	}
	
	@Test //중복ID 검증
	void findMembers() {
		User user1 = new User();
		user1.setId("test2");
		
		User user2 = new User();
		user2.setId("test2");
		
		userService.join(user1);
		
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
	}
	
	@Test
	void findOne() {
		
	}

}
