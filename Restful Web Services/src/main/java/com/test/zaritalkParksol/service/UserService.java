package com.test.zaritalkParksol.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.zaritalkParksol.domin.User;

@Service
public class UserService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount =3; 
	
	//3개의 데이터값이 초기에 들어가있게 설정
	static {
		users.add(new User("1","test1", "김씨", "임대인", "test1", "탈퇴안함", new Date()));
		users.add(new User("2","test2", "박씨", "공인 중개사", "test2", "탈퇴신청", new Date()));
		users.add(new User("3", "test3", "양씨", "임차인", "test3", "탈퇴함", new Date()));
	}
	
	//사용자 전제 목록 조회
	public List<User> findAll() {
		return users;
	}
	
	//사용자 등록
	public User save(User user) {
		if(user.getId() == null) {
			user.setUserNumber(++usersCount);
		}
		
		users.add(user);
		return user;
	}
	
	//사용자 단일 조회
	public User findOne(String id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	//아이디를 통해 삭제
	public User deleteById(String id) {
		
		//나열된 데이터를 순차적으로 확인하기위해
		Iterator<User> iterator = users.iterator();
		
		//순차적으로 하나씩의 데이터를 가져올 수 있도록
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
}
