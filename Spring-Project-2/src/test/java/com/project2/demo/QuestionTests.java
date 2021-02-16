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
import com.project2.demo.beans.Question;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class QuestionTests {
	
	@Autowired
	private DBRepo questionServiceTests;
	
	private int newlyCreatedId;

	
	@Test
	@Order(1)
	void testAddQuestion() {
		
		// First, create a new Question from a pre-existing quiz type (for purposes of this db, call quizId = 4)
		newlyCreatedId = questionServiceTests.addQuestion(new Question(questionServiceTests.getQuiz(4), "Multiple Choice", "What is 9+10?"));
		
		// Then, see if the newly created quiz can be found
		Question testQuestion = questionServiceTests.getQuestion(newlyCreatedId);
		Assertions.assertNotNull(testQuestion);
	}
	
	@Test
	void testGetQuestion() {
		
		// Test to see if the newly created quiz from testAddQuestion() can be found
		Question testQuestion = questionServiceTests.getQuestion(newlyCreatedId);
		Assertions.assertNotNull(testQuestion);
		
	}
	
	@Test
	void testGetAllQuestions() {
		
		// Test to see if there's at least 1 entry (the minimum entry being the question we just created)
		List<Question> testAllQuestions = questionServiceTests.getAllQuestions();
		for(int i = 0; i < testAllQuestions.size(); i++)
			System.out.println(testAllQuestions.get(i));
		Assertions.assertTrue(testAllQuestions.size() > 0);
	}
	
	@Test
	void testUpdateQuestion() {
		
		// Get the current question (via the newly created id)
		Question testQuestion = questionServiceTests.getQuestion(newlyCreatedId);
		
		// Then, update its question description
		testQuestion.setDescription("What is 0 divided by 0?");
		
		// Finally, attempt to update
		Assertions.assertTrue(questionServiceTests.updateQuestion(testQuestion));
	}
	
	@AfterAll
	void deleteAllTests() {
		
		Assertions.assertTrue(questionServiceTests.deleteQuestion(newlyCreatedId));
	}

}
