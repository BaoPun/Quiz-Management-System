package com.project2.repositories;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.project2.beans.Questions;

public class QuestionRepoImpl implements QuestionRepo {

	@Autowired
	SessionFactory sf;

	@Override
	public int addQuestion(Questions q) {
		Session sess = sf.openSession();
		int id = 0;
		
		
		try {
			
			sess.beginTransaction();
			id = Integer.parseInt(sess.save(q).toString());
			sess.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
		} finally {
			sess.close();
		}
		
		return id;
	}


	@Override
	public Questions getQuestion(int id) {
		
		Session sess = sf.openSession();
		Questions q = null;
		
		try {
			q = sess.get(Questions.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return q;
	}


	@Override
	public Questions getQuestion(String name) {
		//I don't think it will needed
		return null;
	}

	@Override
	public List<Questions> getAllQuestions() {
		
		Session sess = sf.openSession();
		List<Questions> questions= null;
		
		try {
			
			questions = sess.createQuery("FROM questions").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		
		return questions;
	}


	@Override
	public Questions updateQuestion(Questions change) {
		
		Session sess = sf.openSession();
		Questions q = null;
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			sess.update(change);
			tx.commit();
			q = change;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
		
		return q;
	}


	@Override
	public boolean deleteQuestion(int id) {
		Session sess = sf.openSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			sess.delete(sess.get(Questions.class, id));
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
		
		return false;
	}


}
