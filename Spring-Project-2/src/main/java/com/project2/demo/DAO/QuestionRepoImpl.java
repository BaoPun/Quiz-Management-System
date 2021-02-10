package com.project2.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.project2.demo.beans.Questions;
import com.project2.demo.beans.User;

public class QuestionRepoImpl implements QuestionRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Questions addQuestion(Questions q) {
		entityManager.persist(q);
		return q;
	}


	@Override
	public Questions getQuestion(int id) {
		Questions q = (Questions)entityManager.find(Questions.class,id);
		return q;
	}


	@Override
	public Questions getQuestion(String name) {
		//I don't think it will needed
		return null;
	}

	@Override
	public List<Questions> getAllQuestions() {
		
		List<Questions> results = entityManager.createQuery("From Questions", Questions.class).getResultList();
		
		return results;
	}


	@Override
	public Questions updateQuestion(Questions change) {
		entityManager.merge(change);
		return change;
	}

/////look at it again
	@Override
	public boolean deleteQuestion(int id) {
		if(entityManager.find(User.class, id) != null) {
			entityManager.remove(id);
			return true;
		}
		entityManager.remove(entityManager.merge(id));
		return false;
	}


}
