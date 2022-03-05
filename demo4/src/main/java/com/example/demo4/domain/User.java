package com.example.demo4.domain;

import java.util.List;

//@Data
public class User {

	private String username;
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String password;
	private String email;
	private int age;
	
	private List<String> roles;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public User(String username, String password, String email, int age, List<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.roles = roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
}
