package com.project2.demo.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AnswerRepoImpl implements AnswerRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
}
