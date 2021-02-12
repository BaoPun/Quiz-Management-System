package com.project2.demo;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.demo.DAO.DBRepoImpl;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;
import com.project2.demo.util.Password;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class UserTests {
	
	@Autowired
	private DBRepoImpl userRepoTests;
	
	private int newlyCreatedId;
	
	// Test out the creation of a new User into the database (as a Student)
	@Test
	@Order(1)
	void testAddStudent() {
		
		// Add a sample User student whose teacher has an id of 8
		newlyCreatedId = userRepoTests.addUser(new User("Karen", Password.hash("test123"), UserType.STUDENT, 8));
		
		// Then see if we can find that user
		User foundTestUser = userRepoTests.getUser(newlyCreatedId);
		System.out.println(foundTestUser);
		Assertions.assertNotNull(foundTestUser);
	}
	
	// Test out the retrieval of a User given their id
	@Test
	void testGetUser() {
		User userTest = userRepoTests.getUser(newlyCreatedId);
		Assertions.assertNotNull(userTest);
	}

	// Same as above, but with their name
	@Test
	void testGetUserByName() {
		User userTest = userRepoTests.getUserByName("Karen");
		if(userTest == null)
			userTest = userRepoTests.getUserByName("AYAYA Clap");
		Assertions.assertNotNull(userTest);
	}
	
	// Retrieve every single User
	@Test
	void testGetAllUsers() {
		List<User> allUsersTest = userRepoTests.getAllUsers();
		Assertions.assertTrue(allUsersTest.size() > 0);
	}
	
	// Retrieve a specific teacher's list of students
	@Test
	void testGetAllStudents() {
		
		// Retrieve all students whose teacher has an id of 8
		List<User> students = userRepoTests.getAllStudents(8);
		for(int i = 0; i < students.size(); i++)
			System.out.println(students.get(i));
		
		Assertions.assertTrue(students.size() > 0);
	}
	
	// Update a User's information
	@Test
	void testUpdateUser() {
		
		// Retrieve the student created via id
		User userChangeTest = userRepoTests.getUser(newlyCreatedId);
		
		// Alter their information: change their username
		userChangeTest.setUsername("AYAYA Clap");
		
		// And then see if the update was successful
		Assertions.assertTrue(userRepoTests.updateUser(userChangeTest));
	}
	
	// After every test passes, delete the User with the example id.
	@AfterAll
	void deleteAllTests() {
		
		// Delete the sample User from testAddUser
		Assertions.assertTrue(userRepoTests.deleteUser(newlyCreatedId));
	}
}
