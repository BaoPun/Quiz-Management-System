package com.project2.demo;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;
import com.project2.demo.util.Password;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class UserTests {
	
	@Autowired
	private UserRepository userRepoTests;
	
	@Test
	void testGetUser() {
		User userTest = userRepoTests.getUser(4);
		Assertions.assertNotNull(userTest);
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
	void testGetAddUser() {
		
		// Add a sample Employee
		userRepoTests.addUser(new User(11, "testing123", Password.hash("test123"), UserType.STUDENT));
		
		// Then see if we can find that user
		Assertions.assertNotNull(userRepoTests.getUser(11));
		
	}
	
	@AfterAll
	void deleteAllTests() {
		
		// Delete the sample User from testGetAddUser
		Assertions.assertTrue(userRepoTests.deleteUser(11));
	}
}
