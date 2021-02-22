package com.project2.demo.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project2.demo.DAO.DBRepo;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;
import com.project2.demo.entities.Engine;
import com.project2.demo.util.Password;


@SpringBootTest(classes = com.project2.demo.SpringProject2Application.class)
class EngineTests {

	@MockBean
	DBRepo services;
	
	@Autowired
	Engine engine;
	
	
	@Test
	void getUserByUsername() throws Exception {
		
		User testUser = new User("Leafa", Password.hash("bar"), UserType.TEACHER, null);
		//if(testUser == null)
		//	fail("testUser returned null");
		Mockito.when(services.getUser("Asuna")).thenReturn(new User(-100, testUser.getUsername(), testUser.getPasswordHash(), testUser.getRole(), testUser.getTeacher()));
		
		int id = engine.getUserByName("Asuna").getId();
		
		Assertions.assertTrue(id == -100);
		
	}
	
	@Test
	void login() throws Exception {
		
		// Mock getUser() from DBRepo and then return a new User with the same username and password.  The other fields are irrelevant
		Mockito.when(services.getUser("Asuna")).thenReturn(new User("Asuna", Password.hash("kirito"), UserType.TEACHER, null));
		
		// Individually test the username and password
		User testUser = engine.getUserByName("Asuna");
		Assertions.assertEquals(testUser.getUsername().toLowerCase(), "asuna");
		Assertions.assertEquals(testUser.getPasswordHash(), Password.hash("kirito"));
		
		// Test out the actual login implementation assuming that the 2 Assertions pass
		Assertions.assertTrue(engine.login(testUser.getUsername(), testUser.getPasswordHash()));
		
	}
	
	@Test
	void register() throws Exception {
		
		// Mock addUser() and getUser() from DBRepo
		User testUser = new User(10, "Charlie", Password.hash("bar"), UserType.TEACHER, null);
		Mockito.when(services.getUser("charlie")).thenReturn(testUser);
		Mockito.when(services.addUser(testUser)).thenReturn(10);
		
		// Then, test the new registration, which uses both getUser() and addUser() in its method.
		Assertions.assertTrue(engine.register(testUser.getUsername(), testUser.getPasswordHash(), testUser.getId()));
	}

}
