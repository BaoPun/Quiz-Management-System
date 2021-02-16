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

import com.project2.demo.DAO.DBRepo;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;
import com.project2.demo.util.Password;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class UserTests {
	
	@Autowired
	private DBRepo userServiceTests;
	
	private int newlyCreatedId;
	
	// Test out the creation of a new User into the database (as a Student)
	@Test
	@Order(1)
	void testAddStudent() {
		
		// Add a sample User student whose teacher has an id of 8
		newlyCreatedId = userServiceTests.addUser(new User("Karen", Password.hash("test123"), UserType.STUDENT, userServiceTests.getUser(8)));
		
		// Then see if we can find that user
		User foundTestUser = userServiceTests.getUser(newlyCreatedId);
		Assertions.assertNotNull(foundTestUser);
	}
	
	// Test out the retrieval of a User given their id
	@Test
	void testGetUser() {
		User userTest = userServiceTests.getUser(newlyCreatedId);
		System.out.println("Found user from testGetUser: " + userTest);
		Assertions.assertNotNull(userTest);
	}

	// Same as above, but with their name
	@Test
	void testGetUserByName() {
		User userTest = userServiceTests.getUser("Karen");
		if(userTest == null)
			userTest = userServiceTests.getUser("AYAYA Clap");
		System.out.println("Found user from testGetUser: " + userTest);
		Assertions.assertNotNull(userTest);
	}
	
	// Retrieve every single User
	@Test
	void testGetAllUsers() {
		List<User> allUsersTest = userServiceTests.getAllUsers();
		Assertions.assertTrue(allUsersTest.size() > 0);
	}
	
	// Retrieve a specific teacher's list of students
	@Test
	void testGetAllStudents() {
		
		// Retrieve all students whose teacher has an id of 8
		List<User> students = userServiceTests.getAllStudents(userServiceTests.getUser(8));
		System.out.println("BEGIN");
		for(int i = 0; i < students.size(); i++)
			System.out.println(students.get(i));
		System.out.println("END");
		
		Assertions.assertTrue(students.size() > 0);
	}
	
	// Update a User's information
	@Test
	void testUpdateUser() {
		
		// Retrieve the student created via id
		User userChangeTest = userServiceTests.getUser(newlyCreatedId);
		
		// Alter their information: change their username
		userChangeTest.setUsername("AYAYA Clap");
		
		userServiceTests.updateUser(userChangeTest);
		
		// And then see if the update was successful
		Assertions.assertTrue(userServiceTests.getUser("AYAYA Clap").getUsername().equals("AYAYA Clap"));
	}
	
	// After every test passes, delete the User with the example id.
	@AfterAll
	void deleteAllTests() {
		
		// Delete the sample User from testAddUser
		Assertions.assertTrue(userServiceTests.deleteUser(newlyCreatedId));
	}
}
