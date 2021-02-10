package com.project2.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.User;

@SpringBootTest
class SpringProject2ApplicationTests {
	
	@Autowired
	private UserRepository userRepoTests;

	@Test
	void contextLoads() {
		//System.out.println();
	}
	
	
	@Test
	void testGetUser() {
		User userTest = userRepoTests.getUserByName("alice");
		Assertions.assertNotNull(userTest);
	}

}
