package com.project2.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Permission;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.util.Password;



@Component
@Transactional
public class UserRepository implements DBInterface {
	@PersistenceContext
	private EntityManager entityManager;
	
	// Question
	/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Question addQuestion(Question a) {
		entityManager.persist(a);
		return a;
	}

	@Override
	public Question getQuestion(int id) {
		return (Question) entityManager.find(Question.class, id);
	}

	@Override
	public List<Question> getAllQuestions() {
		return entityManager.createQuery("from Questions").getResultList();
	}

	@Override
	public Question updateQuestion(Question change) {
		entityManager.merge(change);
		return change;
	}

	@Override
	public boolean deleteQuestion(int id) {
		Question response = (Question) entityManager.find(Question.class, id);
		if (entityManager.contains(response)) {
			entityManager.remove(response);
			return true;
		} 

		return false;
	
	}
	
	
	// Answer
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Answer addAnswer(Answer a) {
		entityManager.persist(a);
		return a;
	}

	@Override
	public Answer getAnswer(int id) {
		Answer a=(Answer) entityManager.find(Answer.class, id);
		return a;
	}

	@Override
	public List<Answer> getAllAnswer() {
		return entityManager.createQuery("from Answers").getResultList();
	}

	@Override
	public Answer updateAnswer(Answer change) {
		entityManager.merge(change);
		return change;
	}

	@Override
	public boolean deleteAnswer(int id) {
		Answer response = (Answer) entityManager.find(Answer.class, id);
		if (entityManager.contains(response)) {
			entityManager.remove(response);
			return true;
		} 

		return false;
	
	}
	
	// Progress
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Progress addProgress(Progress a) {
		entityManager.persist(a);
		return a;
	}

	@Override
	public Progress getProgress(int id) {
		Progress response = (Progress) entityManager.find(Progress.class, id);
		return response;
	}

	@Override
	public List<Progress> getAllProgress() {
		return entityManager.createQuery("from Progress").getResultList();
	}

	@Override
	public Progress updateProgress(Progress change) {
		entityManager.merge(change);
		return change;
	}

	@Override
	public boolean deleteProgress(int id) {
		Progress response = (Progress) entityManager.find(Progress.class, id);
		if (entityManager.contains(response)) {
			entityManager.remove(response);
			return true;
		} 

		return false;
	
	}
	
	// Quiz
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Quiz addQuiz(Quiz a) {
		entityManager.persist(a);
		return a;
	}

	@Override
	public Quiz getQuiz(int id) {
		Quiz response = (Quiz) entityManager.find(Quiz.class, id);
		return response;
	}

	@Override
	public List<Quiz> getAllQuiz() {
		return entityManager.createQuery("from Quizzes").getResultList();
	}

	@Override
	public Quiz updateQuiz(Quiz change) {
		entityManager.merge(change);
		return change;
	}

	@Override
	public boolean deleteQuiz(int id) {
		Quiz response = (Quiz) entityManager.find(Quiz.class, id);
		if (entityManager.contains(response)) {
			entityManager.remove(response);
			return true;
		} 

		return false;
	
	}
	
	
	// User
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean addUser(User a) {
		entityManager.createNativeQuery("INSERT INTO Users VALUES (?1, ?2, ?3, ?4, ?5)").setParameter(1, a.getId())
		.setParameter(2, a.getUsername()).setParameter(3, Password.hash(a.getPasswordHash()))
		.setParameter(4, 4).setParameter(5, a.getRole().STUDENT.toString()).executeUpdate();
		
		return true;
	}

	@Override
	public User getUser(int id) {
		User response = (User) entityManager.find(User.class, id);
		return response;
	}
	
	@Override
	public User getUserByName(String name) {
		TypedQuery<User> tq = entityManager.createQuery("from User WHERE username=?1",User.class);
		return tq.setParameter(1, name).getSingleResult();
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>)entityManager.createQuery("from User").getResultList();
	}

	@Override
	public User updateUser(User change) {
		entityManager.merge(change);
		return change;
	}

	@Override
	public boolean deleteUser(int id) {
		User response = (User) entityManager.find(User.class, id);
		if (entityManager.contains(response)) {
			entityManager.remove(response);
			return true;
		} 

		return false;
	
	}
	
	// Permission
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Permission addPermission(Permission a) {
		entityManager.persist(a);
		return a;
	}

	@Override
	public Permission getPermission(int id) {
		Permission response = (Permission) entityManager.find(Permission.class, id);
		return response;
	}

	@Override
	public List<Permission> getAllPermission() {
		return entityManager.createQuery("from Permissions").getResultList();
	}

	@Override
	public Permission updatePermission(Permission change) {
		entityManager.merge(change);
		return change;
	}

	@Override
	public boolean deletePermission(int id) {
		Permission response = (Permission) entityManager.find(Permission.class, id);
		if (entityManager.contains(response)) {
			entityManager.remove(response);
			return true;
		} 

		return false;
	
	}
	

}
