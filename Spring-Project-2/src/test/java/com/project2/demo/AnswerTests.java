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
import com.project2.demo.beans.Answer;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class AnswerTests {
	
	@Autowired
	private DBRepo answerServiceTests;
	
	private int newlyCreatedId;

	@Test
	@Order(1)
	void testAddAnswer() {
		
		// First, create a new Answer for a question (the question, the answer, is it correct?, answer order)
		newlyCreatedId = answerServiceTests.addAnswer(new Answer(answerServiceTests.getQuestion(21), "21", false, 2));
				
		// Afterwards, find that answer
		Assertions.assertNotNull(answerServiceTests.getAnswer(newlyCreatedId));
	}
	
	@Test
	void testGetAnswer() {
		Assertions.assertNotNull(answerServiceTests.getAnswer(newlyCreatedId));
	}
	
	@Test
	void testGetAllAnswers() {
		// The parameter is actually going to be a pre-existing question id.
		List<Answer> testAllAnswers = answerServiceTests.getAllAnswers(21);
		
		System.out.println("START");
		for(int i = 0; i < testAllAnswers.size(); i++)
			System.out.println(testAllAnswers.get(i));
		System.out.println("END");
		
		Assertions.assertTrue(testAllAnswers.size() > 0);
	}
	
	@Test
	void testUpdateAnswer() {
		
		// Get the current answer
		Answer newAnswer = answerServiceTests.getAnswer(newlyCreatedId);
		
		// And update it with a new answer
		newAnswer.setAnswerText("20");
		
		// Afterwards, update it
		Assertions.assertTrue(answerServiceTests.updateAnswer(newAnswer));
	}
	
	@AfterAll
	void deleteAllTests() {
		Assertions.assertTrue(answerServiceTests.deleteAnswer(newlyCreatedId));
	}
}
