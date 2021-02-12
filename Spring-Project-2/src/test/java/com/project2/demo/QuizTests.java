package com.project2.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.demo.DAO.DBRepoImpl;
import com.project2.demo.beans.Quiz;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class QuizTests {
	
	@Autowired
	private DBRepoImpl userRepoTests;
	
	private int newlyCreatedId;

	@Test
	void testAddQuiz() {
		
		// First, create a new Quiz (using a Teacher with id of 8)
		newlyCreatedId = userRepoTests.addQuiz(new Quiz("Sample Quiz", userRepoTests.getUser(8)));
		
		// Then, see if the newly created quiz can be found
		Quiz testQuiz = userRepoTests.getQuiz(newlyCreatedId);
		System.out.println(testQuiz);
		Assertions.assertNotNull(testQuiz);
	}
	
	@AfterAll
	void deleteAllTests() {
		
		// Delete the sample test quiz created from testAddQuiz
		Assertions.assertTrue(userRepoTests.deleteQuiz(newlyCreatedId));
	}

}
