package com.example.demo4.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo4.domain.User;

//@Repository
public class MemoryUserRepository implements UserRepository{
	
	//저장해놔야하니깐
	private static Map<String, User> store = new HashMap<>();
	
	@Override
	public User save(User user) {
		store.put(user.getId(), user);
		return user;
	}

	@Override
	public Optional<User> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id)); //결과가 없으면 null
	}

	@Override
	public Optional<User> findByusername(String username) {
		
		return store.values().stream().filter(user -> user.getUsername().equals(username)).findAny();
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(store.values());
	}
	
	//Test와 관련, 테스트 수행 후 그 테스트 끝나면 클리어
	public void clearStore() {
		store.clear();
	}

}
