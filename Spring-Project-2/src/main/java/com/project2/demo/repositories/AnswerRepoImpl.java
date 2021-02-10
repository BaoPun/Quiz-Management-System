package com.project2.demo.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AnswerRepoImpl implements AnswerRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
}
