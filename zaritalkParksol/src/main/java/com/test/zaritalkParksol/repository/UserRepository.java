package com.test.zaritalkParksol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.zaritalkParksol.domin.User;

public interface UserRepository extends JpaRepository<User, String>{

}
