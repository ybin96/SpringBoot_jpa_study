package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// JAP Naming 쿼리
	// select * from user where username = ? AND password = ?;
//	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value = "SELECT * FROM user WHERE username =?1 AND password =?2", nativeQuery = true)
//	User login(String username, String password);
	
	
	// select * from user where username=1?;
	Optional<User> findByUsername(String username);
}
