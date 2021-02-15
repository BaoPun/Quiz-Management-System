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

import com.project2.demo.beans.Permission;
import com.project2.demo.beans.PermissionType;
import com.project2.demo.services.DBService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class PermissionTests {
	
	@Autowired
	private DBService permissionServiceTests;
	
	private int newlyCreatedId;

	@Test
	@Order(1)
	void testAddPermission() {
		
		// First, add a new Permission (with a specific User and Quiz type that's alreaedy pre-loaded)
		newlyCreatedId = permissionServiceTests.addPermission(new Permission(permissionServiceTests.getUser(4), permissionServiceTests.getQuiz(4), PermissionType.VISIBLE));
		
		// Then, test to see if that Permission id exists
		Assertions.assertNotNull(permissionServiceTests.getPermission(newlyCreatedId));
	}
	
	@Test
	void testGetPermission() {
		
		// test to see if that Permission id exists
		Permission testPermission = permissionServiceTests.getPermission(newlyCreatedId);
		System.out.println("Permission JSON: " + testPermission);
		Assertions.assertNotNull(testPermission);
	}
	
	@Test
	void testGetAllPermissions() {
		List<Permission> permissionsById = permissionServiceTests.getAllPermissions(newlyCreatedId);
		
		System.out.println("START");
		for(int i = 0; i < permissionsById.size(); i++)
			System.out.println(permissionsById.get(i));
		System.out.println("END");
	}
	
	@AfterAll
	void deleteAllTests() {
		
		// Delete the testing permission
		Assertions.assertTrue(permissionServiceTests.deletePermission(newlyCreatedId));
	}

}
