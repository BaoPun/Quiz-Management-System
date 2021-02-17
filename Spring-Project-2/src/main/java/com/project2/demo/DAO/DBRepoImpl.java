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
import com.project2.demo.beans.UserType;


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
			id = -1;
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
	public User getUser(String name) {
		try {
			TypedQuery<User> tq = sf.createEntityManager().createQuery("from User WHERE username=?1",User.class);
			return tq.setParameter(1, name).getSingleResult();
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
	public List<User> getAllTeachers() {
		return sf.createEntityManager().
				createQuery("from User where role=:role")
				.setParameter("role", UserType.TEACHER)
				.getResultList();
	}
	
	@Override
	public List<User> getAllStudents(User teacher){
		
		// This goes in every single method
		Session session = sf.openSession();
		List<User> listOfStudents = null;
		
		try {
			// "User" portion MUST match the Java object, NOT the table.
			// :id is simply a ? variant
			listOfStudents = session.createQuery("FROM User WHERE teacher = :id AND role = :role")
					.setParameter("id", teacher)
					.setParameter("role", UserType.STUDENT).getResultList();	
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
		
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(change);
			transaction.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
		finally {
			session.close();
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
	
	// Quiz
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addQuiz(Quiz a) {

		Session session = sf.openSession();
		
		// First, obtain the next unique id from the quiz id sequence generator
		int id = sf.createEntityManager().createNativeQuery("SELECT quiz_id_generator.nextval FROM DUAL").getFirstResult();
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
	public Quiz getQuiz(int id) {
		return (Quiz) sf.createEntityManager().find(Quiz.class, id);
	}
	
	@Override
	public List<Quiz> getQuizzes(String name) {
		TypedQuery<Quiz> tq = sf.createEntityManager().createQuery("from Quiz WHERE name=?1",Quiz.class);
		return tq.setParameter(1, name).getResultList();
	}
	
	@Override
	public List<Quiz> getQuizzesFromUser(int id) {
		return sf.createEntityManager().
				createQuery("from Quiz where userid=?1").
				setParameter(1, id).getResultList();
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		// With CreateQuery, Quiz is the Java object, not the table
		return sf.createEntityManager().createQuery("from Quiz").getResultList();
	}

	@Override
	public boolean updateQuiz(Quiz change) {
		
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(change);
			transaction.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	@Override
	public boolean deleteQuiz(int id) {
		// This goes in every single method
		Session session = sf.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();	
			session.delete(session.get(Quiz.class, id));
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
	public int addQuestion(Question a) {
		Session session = sf.openSession();
		
		// First, obtain the next unique id from the quiz id sequence generator
		int id = sf.createEntityManager().createNativeQuery("SELECT question_id_generator.nextval FROM DUAL").getFirstResult();
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
	public Question getQuestion(int id) {
		return (Question) sf.createEntityManager().find(Question.class, id);
	}

	@Override
	public List<Question> getAllQuestions() {
		return sf.createEntityManager().createQuery("from Question").getResultList();
	}

	@Override
	public boolean updateQuestion(Question change) {
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(change);
			transaction.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	@Override
	public boolean deleteQuestion(int id) {
		// This goes in every single method
		Session session = sf.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();	
			session.delete(session.get(Question.class, id));
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
	
	
	// Answer
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addAnswer(Answer a) {
		Session session = sf.openSession();
		
		// First, obtain the next unique id from the quiz id sequence generator
		int id = sf.createEntityManager().createNativeQuery("SELECT answer_id_generator.nextval FROM DUAL").getFirstResult();
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
	public Answer getAnswer(int id) {
		return (Answer) sf.createEntityManager().find(Answer.class, id);
	}

	@Override
	public List<Answer> getAllAnswers(int id) {
		// Get all Answers registered by a particular user id
		Session session = sf.openSession();
		List<Answer> listOfAnswers = null;
		
		try {
			// "User" portion MUST match the Java object, NOT the table.
			// :id is simply a ? variant
			listOfAnswers = session.createQuery("FROM Answer WHERE questionId = ?1")
					.setParameter(1, id).getResultList();
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfAnswers;
		
	}

	@Override
	public boolean updateAnswer(Answer change) {
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(change);
			transaction.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	@Override
	public boolean deleteAnswer(int id) {
		// This goes in every single method
		Session session = sf.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();	
			session.delete(session.get(Answer.class, id));
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
	
	// Progress
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addProgress(Progress a) {
		Session session = sf.openSession();
		
		// First, obtain the next unique id from the quiz id sequence generator
		int id = sf.createEntityManager().createNativeQuery("SELECT progress_id_generator.nextval FROM DUAL").getFirstResult();
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
	public Progress getProgress(int id) {
		Progress response = (Progress) sf.createEntityManager().find(Progress.class, id);
		return response;
	}

	@Override
	public List<Progress> getAllProgress(int id) {
		// This goes in every single method
		Session session = sf.openSession();
		List<Progress> listOfProgress = null;
		
		try {
			// "User" portion MUST match the Java object, NOT the table.
			// :id is simply a ? variant
			listOfProgress = session.createQuery("FROM Progress WHERE userId = ?1")
					.setParameter(1, id).getResultList();
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfProgress;

	}

	@Override
	public boolean updateProgress(Progress change) {
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(change);
			transaction.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	@Override
	public boolean deleteProgress(int id) {
		// This goes in every single method
		Session session = sf.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();	
			session.delete(session.get(Progress.class, id));
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
	
	
	
	
	// Permission
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int addPermission(Permission a) {
		Session session = sf.openSession();
		
		// First, obtain the next unique id from the quiz id sequence generator
		int id = sf.createEntityManager().createNativeQuery("SELECT permission_id_generator.nextval FROM DUAL").getFirstResult();
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
	public Permission getPermission(int id) {
		Permission response = (Permission) sf.createEntityManager().find(Permission.class, id);
		return response;
	}

	@Override
	public List<Permission> getAllPermissions() {
		return sf.createEntityManager().createQuery("from Permission").getResultList();
	}
	
	@Override
	public List<Permission> getAllPermissions(int id) {
		// Get all Permissions by user id
		Session session = sf.openSession();
		List<Permission> listOfPermissions = null;
		
		try {
			// "User" portion MUST match the Java object, NOT the table.
			// :id is simply a ? variant
			listOfPermissions = session.createQuery("FROM Permission WHERE userId = ?1")
					.setParameter(1, id).getResultList();
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfPermissions;
	}

	@Override
	public boolean updatePermission(Permission change) {
		Session session = sf.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(change);
			transaction.commit();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}
		finally {
			session.close();
		}
	}

	@Override
	public boolean deletePermission(int id) {
		// This goes in every single method
		Session session = sf.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();	
			session.delete(session.get(Permission.class, id));
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


	

}
