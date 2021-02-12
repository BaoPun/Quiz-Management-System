package com.project2.demo.DAO;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Permission;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;


@Component
@Transactional
public class DBRepoImpl implements DBRepo {
	
	@Autowired
	private SessionFactory sf;
	
	// User
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addUser(User a) {
		
		Session session = sf.openSession();
		int id = sf.createEntityManager().createNativeQuery("SELECT user_id_generator.nextval FROM DUAL").getFirstResult();
		a.setId(id);
		
		//DML statements use transactions
		try {
			session.beginTransaction();
			id = Integer.parseInt(session.save(a).toString());
			session.getTransaction().commit();
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} 
		finally {
			session.close();
		}
		
		return id;

	}

	@Override
	public User getUser(int id) {
		User response = (User) sf.createEntityManager().find(User.class, id);
		return response;
	}
	
	@Override
	public User getUserByName(String name) {
		try {
			TypedQuery<User> tq = sf.createEntityManager().createQuery("from User WHERE lower(username)=?1",User.class);
			return tq.setParameter(1, name.toLowerCase()).getSingleResult();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>)sf.createEntityManager().createQuery("from User").getResultList();
	}
	
	@Override
	public List<User> getAllStudents(int id){
		
		// This goes in every single method
		Session session = sf.openSession();
		List<User> listOfStudents = null;
		
		try {
			// "User" portion MUST match the Java object, NOT the table.
			// :id is simply a ? variant
			listOfStudents = session.createQuery("FROM User WHERE teacherId = :id")
					.setParameter("id", id).getResultList();	
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return listOfStudents;
	}

	@Override
	public boolean updateUser(User change) {
		try {
			sf.createEntityManager().merge(change);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(int id) {
		
		// This goes in every single method
		Session session = sf.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();	
			session.delete(session.get(User.class, id));
			transaction.commit();
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}
		finally {
			session.close();
		}
		
		return false;
	}
		
	
	// Question
	/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Question addQuestion(Question a) {
		sf.createEntityManager().persist(a);
		return a;
	}

	@Override
	public Question getQuestion(int id) {
		return (Question) sf.createEntityManager().find(Question.class, id);
	}

	@Override
	public List<Question> getAllQuestions() {
		return sf.createEntityManager().createQuery("from Questions").getResultList();
	}

	@Override
	public Question updateQuestion(Question change) {
		sf.createEntityManager().merge(change);
		return change;
	}

	@Override
	public boolean deleteQuestion(int id) {
		Question response = (Question) sf.createEntityManager().find(Question.class, id);
		if (sf.createEntityManager().contains(response)) {
			sf.createEntityManager().remove(response);
			return true;
		} 

		return false;
	
	}
	
	
	// Answer
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Answer addAnswer(Answer a) {
		sf.createEntityManager().persist(a);
		return a;
	}

	@Override
	public Answer getAnswer(int id) {
		Answer a=(Answer) sf.createEntityManager().find(Answer.class, id);
		return a;
	}

	@Override
	public List<Answer> getAllAnswer() {
		return sf.createEntityManager().createQuery("from Answers").getResultList();
	}

	@Override
	public Answer updateAnswer(Answer change) {
		sf.createEntityManager().merge(change);
		return change;
	}

	@Override
	public boolean deleteAnswer(int id) {
		Answer response = (Answer) sf.createEntityManager().find(Answer.class, id);
		if (sf.createEntityManager().contains(response)) {
			sf.createEntityManager().remove(response);
			return true;
		} 

		return false;
	
	}
	
	// Progress
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Progress addProgress(Progress a) {
		sf.createEntityManager().persist(a);
		return a;
	}

	@Override
	public Progress getProgress(int id) {
		Progress response = (Progress) sf.createEntityManager().find(Progress.class, id);
		return response;
	}

	@Override
	public List<Progress> getAllProgress() {
		return sf.createEntityManager().createQuery("from Progress").getResultList();
	}

	@Override
	public Progress updateProgress(Progress change) {
		sf.createEntityManager().merge(change);
		return change;
	}

	@Override
	public boolean deleteProgress(int id) {
		Progress response = (Progress) sf.createEntityManager().find(Progress.class, id);
		if (sf.createEntityManager().contains(response)) {
			sf.createEntityManager().remove(response);
			return true;
		} 

		return false;
	
	}
	
	// Quiz
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Quiz addQuiz(Quiz a) {
		sf.createEntityManager().persist(a);
		return a;
	}

	@Override
	public Quiz getQuiz(int id) {
		Quiz response = (Quiz) sf.createEntityManager().find(Quiz.class, id);
		return response;
	}

	@Override
	public List<Quiz> getAllQuiz() {
		return sf.createEntityManager().createQuery("from Quizzes").getResultList();
	}

	@Override
	public Quiz updateQuiz(Quiz change) {
		sf.createEntityManager().merge(change);
		return change;
	}

	@Override
	public boolean deleteQuiz(int id) {
		Quiz response = (Quiz) sf.createEntityManager().find(Quiz.class, id);
		if (sf.createEntityManager().contains(response)) {
			sf.createEntityManager().remove(response);
			return true;
		} 

		return false;
	
	}
	
	
	
	// Permission
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Permission addPermission(Permission a) {
		sf.createEntityManager().persist(a);
		return a;
	}

	@Override
	public Permission getPermission(int id) {
		Permission response = (Permission) sf.createEntityManager().find(Permission.class, id);
		return response;
	}

	@Override
	public List<Permission> getAllPermission() {
		return sf.createEntityManager().createQuery("from Permissions").getResultList();
	}

	@Override
	public Permission updatePermission(Permission change) {
		sf.createEntityManager().merge(change);
		return change;
	}

	@Override
	public boolean deletePermission(int id) {
		Permission response = (Permission) sf.createEntityManager().find(Permission.class, id);
		if (sf.createEntityManager().contains(response)) {
			sf.createEntityManager().remove(response);
			return true;
		} 

		return false;
	
	}
	

}
