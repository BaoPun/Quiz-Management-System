package com.project2.demo.DAO;

import java.util.List;
import java.math.BigDecimal;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
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
	
	// Helper function for addUser: check if the username is unique, case insensitive
	// Converting from BigDecimal to Integer is such a pain in the arse...
	public boolean isUniqueUser(String user) {
		Session session = sf.openSession();
		int count = Integer.MIN_VALUE;
		try {
			List<BigDecimal> counts = sf.createEntityManager().createNativeQuery("SELECT COUNT(username) FROM Users WHERE LOWER(username) = :username")
					.setParameter("username", user.toLowerCase())
					.getResultList();
			count = counts.get(0).intValueExact();
			System.out.println("Is this unique: " + (count == 0 ? "yes" : "no"));
			
		}
		catch(ConstraintViolationException e) {
			return true;
		}
		catch(HibernateException e) {
			
		}
		finally {
			session.close();
		}
		return count == 0;	// if the user is unique, then the count should be 0
		
	}
	
	@Override
	public int addUser(User a) {
		
		// If the username is not unique, then return -1 immediately.
		if(!isUniqueUser(a.getUsername()))
			return -1;
		
		Session session = sf.openSession();
		int id = sf.createEntityManager().createNativeQuery("SELECT user_id_generator.nextval FROM DUAL").getFirstResult();
		a.setId(id);
		
		//DML statements use transactions
		try {
			session.beginTransaction();
			id = Integer.parseInt(session.save(a).toString());
			session.getTransaction().commit();
		} 
		catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			id = -1;
		} 
		catch(HibernateException e) {
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
			TypedQuery<User> tq = sf.createEntityManager().createQuery("from User WHERE LOWER(username)=?1",User.class);
			return tq.setParameter(1, name.toLowerCase()).getSingleResult();
		}
		catch(Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		return (List<User>)sf.createEntityManager().createQuery("from User").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllTeachers() {
		return sf.createEntityManager().
				createQuery("from User where role=:role")
				.setParameter("role", UserType.TEACHER)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllStudents(User teacher){
		
		// This goes in every single method
		Session session = sf.openSession();
		List<User> listOfStudents = null;
		
		try {
			// "User" portion MUST match the Java object, NOT the table.
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserStudents(int teacherid) {
		return sf.createEntityManager().
				createQuery("from User u where u.teacher.id=?1").
				setParameter(1, teacherid).
				getResultList();
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
		TypedQuery<Quiz> tq = sf.createEntityManager().createQuery("from Quiz WHERE LOWER(name)=?1",Quiz.class);
		return tq.setParameter(1, name.toLowerCase()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Quiz> getQuizzesFromUser(int id) {
		return sf.createEntityManager().
				createQuery("from Quiz where userid=?1").
				setParameter(1, id).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quiz> getAllQuizzes() {
		// With CreateQuery, Quiz is the Java object, not the table
		return sf.createEntityManager().createQuery("from Quiz").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Quiz> getQuizzesStartedByStudent(int studentid) {
		return sf.createEntityManager().
				createQuery("select distinct p.answer.question.quiz from Progress p where p.user.id=?1").
				setParameter(1, studentid).
				getResultList();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestions() {
		return sf.createEntityManager().createQuery("from Question").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuizQuestions(int quizid) {
		return sf.createEntityManager().
				createQuery("from Question q where q.quiz.id=?1 order by q.ordering").
				setParameter(1, quizid).
				getResultList();
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

	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getQuestionAnswers(int questionid) {
		return sf.createEntityManager().
				createQuery("from Answer a where a.question.id=?1 order by a.ordering").
				setParameter(1, questionid).
				getResultList();
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

	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Progress> getProgressForUserAndQuiz(int quizid, int userid) {
		return sf.createEntityManager().
				createQuery("from Progress p where p.answer.question.quiz.id=?1 and p.user.id=?2 order by p.answer.ordering asc").
				setParameter(1, quizid).
				setParameter(2, userid).
				getResultList();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getAllPermissions() {
		return sf.createEntityManager().createQuery("from Permission").getResultList();
	}

	@SuppressWarnings("unchecked")
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
