package com.test.zaritalk_parksol;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.zaritalk_parksol.domain.User;
import com.test.zaritalk_parksol.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	//엔티티메니저를 통한 모든 데이터변경은 항상 트랜잭션 안에 이뤄져야함으로 Transactional 꼭 필요함
	@Transactional //테스트를 끝낸 다음에 롤백실행
	public void TestUser() throws Exception{
		
		User user = new User();
		user.setNickname("김씨");
		
		Long saveId = userRepository.save(user);
		User findUser = userRepository.find(saveId);
		
		//검증하기
		Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
		Assertions.assertThat(findUser.getNickname()).isEqualTo(user.getNickname());
		Assertions.assertThat(findUser).isEqualTo(user);
		
	}
}
