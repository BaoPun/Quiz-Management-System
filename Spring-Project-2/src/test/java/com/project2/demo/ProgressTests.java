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
import com.project2.demo.beans.Progress;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class ProgressTests {
	
	@Autowired
	private DBRepo progressServiceTests;
	
	private int newlyCreatedId;

	@Test
	@Order(1)
	void testAddProgress() {
		
		// Add a new Progress
		newlyCreatedId = progressServiceTests.addProgress(new Progress(progressServiceTests.getUser(4), progressServiceTests.getAnswer(1)));
		
		// Check to see if that Progress can be found
		Progress testProgress = progressServiceTests.getProgress(newlyCreatedId);
		//System.out.println(testProgress);
		Assertions.assertNotNull(testProgress);
	}
	
	@Test
	void testGetProgress() {
		Assertions.assertNotNull(progressServiceTests.getProgress(newlyCreatedId));
	}
	
	@Test
	void testGetAllProgress() {
		
		// Get all chosen answers by a particular user
		List<Progress> listOfChosenAnswers = progressServiceTests.getAllProgress(4);
		System.out.println("START");
		for(int i = 0; i < listOfChosenAnswers.size(); i++)
			System.out.println(listOfChosenAnswers.get(i));
		System.out.println("END");
		
		Assertions.assertTrue(listOfChosenAnswers.size() > 0);
	}
	
	@AfterAll
	void deleteAllTests() {
		Assertions.assertTrue(progressServiceTests.deleteProgress(newlyCreatedId));
	}

}
