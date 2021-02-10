package com.project2.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;



@Component
@Transactional
public class UserRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public UserRepository()  {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 	public Questions addQuestion(Questions a);
	public Questions getQuestion(int id);
	public Questions getQuestion(String name);
	public List<Questions> getAllQuestions();
	public Questions updateQuestion(Questions change);
	public boolean deleteQuestion(int id);
	 */

	
	public Question addQuestion(Question a) {
		entityManager.persist(a);
		return a;
	}
	
	public Question getQuestion(int id) {
		return entityManager.find(Question.class,id);
	}
	
//	public User getAllUsers() {
//		TypedQuery<User> tq = entityManager.createQuery("from user where username)
//	}
	
	public User getUserByName(String name) {
		TypedQuery<User> tq = entityManager.createQuery("from User WHERE username=?1",User.class);
		return tq.setParameter(1, name).getSingleResult();
	}
	
	public User getThing(int id) {
		return entityManager.find(User.class, id);
	}
	
	public void addThing(User user) {
		entityManager.persist(user);
	}
	

	public Quiz getQuiz(int id) {
		return entityManager.find(Quiz.class,id);
	}

}
