package com.project2.repositories;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.project2.beans.Questions;

public class QuestionRepoImpl implements QuestionRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int addQuestion(Questions q) {
		entityManager.persist(q);
		return q;
	}


////////////
	@Override
	public Questions getQuestion(int id) {
		Questions q = (Question)entityManager.find(Question.class,id);
		return q;
	}


	@Override
	public Questions getQuestion(String name) {
		//I don't think it will needed
		return null;
	}

	@Override
	public List<Questions> getAllQuestions() {
		
		List<Questions> results = entityManager.createQuery("From Questions", Question.class)
                .getResultList();
		
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
		if(entityManager.find(User.class, id)) {
			entityManger.remove(id);
			return true;
		}
		else {
			entityManger.remove(entityManger.merge(id));
			}
		return false;
		}


}
