package com.project2.demo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.User;
import com.project2.demo.util.Password;

@SpringBootTest
class SpringProject2ApplicationTests {
	
	@Autowired
	private UserRepository userRepoTests;

	@Test
	void contextLoads() {
		System.out.println(Password.hash("test"));
	}
	
	
	@Test
	void testGetUserByName() {
		User userTest = userRepoTests.getUserByName("alice");
		Assertions.assertNotNull(userTest);
	}
	
	@Test
	void testGetAllUsers() {
		List<User> allUsersTest = userRepoTests.getAllUsers();
		Assertions.assertTrue(allUsersTest.size() > 0);
	}
	
	@Test
	void testGetQuestion() {
		/*
		Question questionTest = userRepoTests.getQuestion(1);	// For now, get the first question
		Assertions.assertNotNull(questionTest);
		*/
	}

}
