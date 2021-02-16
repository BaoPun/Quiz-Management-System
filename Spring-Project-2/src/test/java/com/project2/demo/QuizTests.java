package com.project2.demo;

import static org.assertj.core.api.Assertions.fail;

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
import com.project2.demo.beans.Quiz;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class QuizTests {
	
	@Autowired
	private DBRepo quizServiceTests;
	
	private int newlyCreatedId;

	@Test
	@Order(1)
	void testAddQuiz() {
		
		// First, create a new Quiz (using a Teacher with id of 8)
		newlyCreatedId = quizServiceTests.addQuiz(new Quiz("Sample Quiz", quizServiceTests.getUser(8)));
		
		// Then, see if the newly created quiz can be found
		Quiz testQuiz = quizServiceTests.getQuiz(newlyCreatedId);
		Assertions.assertNotNull(testQuiz);
	}
	
	@Test
	void testGetQuiz() {
		
		// Retrieve the quiz via the id generated via testAddQuiz
		Quiz testQuiz = quizServiceTests.getQuiz(newlyCreatedId);
		Assertions.assertNotNull(testQuiz);	
	}
	
	@Test
	void testGetAllQuizzes() {
			
		List<Quiz> quizList = quizServiceTests.getAllQuizzes();
		System.out.println("START");
		for(int i = 0; i < quizList.size(); i++)
			System.out.println(quizList.get(i));
		System.out.println("END");
		Assertions.assertTrue(quizList.size() > 0);
	}
	
	@Test
	void testUpdateQuiz() {
		
		// First retrieve the quiz via the id
		Quiz changeQuiz = quizServiceTests.getQuiz(newlyCreatedId);
		
		// Then, update the name of the quiz and send that to the DB
		changeQuiz.setName("Imagine imagining");
		if(!quizServiceTests.updateQuiz(changeQuiz))
			fail("Oh no the update failed!");
		
		// Finally, attempt to retrieve it
		Assertions.assertTrue(quizServiceTests.getQuizzes("Imagine imagining").size()>0);
	}
	
	@AfterAll
	void deleteAllTests() {
		
		// Delete the sample test quiz created from testAddQuiz
		Assertions.assertTrue(quizServiceTests.deleteQuiz(newlyCreatedId));
	}

}
