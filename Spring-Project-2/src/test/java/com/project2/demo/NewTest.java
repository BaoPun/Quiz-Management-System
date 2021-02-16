package com.project2.demo;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.demo.DAO.DBRepoImpl;
import com.project2.demo.beans.Question;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)		// For each test, we want to test add operations first
											// hence, add tests have priority over the others
class NewTest {
	
	@Autowired
	private DBRepoImpl answerServiceTests;
	

	@Test
	@Order(1)
	void testAddAnswer() {
		SessionFactory sf = answerServiceTests.getSF();
		List<Question> qlist = answerServiceTests.getAllQuestions();
		Question q = qlist.get(0);
		System.out.println(sf.createEntityManager().createQuery("from Progress where answer.question=?1").setParameter(1, q).getResultList());
	}

}
