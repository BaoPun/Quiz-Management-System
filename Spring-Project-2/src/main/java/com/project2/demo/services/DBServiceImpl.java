package com.project2.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.demo.DAO.DBRepo;
import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Permission;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;

@Component
@Transactional
public class DBServiceImpl implements DBService {
	
	@Autowired
	private DBRepo dbRepo;

	// Users
	public int addUser(User a) {
		return dbRepo.addUser(a);
	}
	
	public User getUser(int id) {
		return dbRepo.getUser(id);
	}
	
	public User getUser(String user) {
		return dbRepo.getUser(user);
	}
	
	public List<User> getAllUsers() {
		return dbRepo.getAllUsers();
	}
	
	public List<User> getAllStudents(User teacher) {
		return dbRepo.getAllStudents(teacher);
	}
	
	public boolean updateUser(User change) {
		return dbRepo.updateUser(change);
	}
	
	public boolean deleteUser(int id) {
		return dbRepo.deleteUser(id);
	}
	
	
	//Quizzes 
	public int addQuiz(Quiz a) {
		return dbRepo.addQuiz(a);
	}
	
	public Quiz getQuiz(int id) {
		return dbRepo.getQuiz(id);
	}
	
	public Quiz getQuiz(String name) {
		return dbRepo.getQuiz(name);
	}
	
	public List<Quiz> getAllQuizzes() {
		return dbRepo.getAllQuizzes();
	}
	
	public boolean updateQuiz(Quiz change) {
		return dbRepo.updateQuiz(change);
	}
	
	public boolean deleteQuiz(int id) {
		return dbRepo.deleteQuiz(id);
	}
	
	
	//Questions
	public int addQuestion(Question a) {
		return dbRepo.addQuestion(a);
	}
	
	public Question getQuestion(int id) {
		return dbRepo.getQuestion(id);
	}
	
	public List<Question> getAllQuestions() {
		return dbRepo.getAllQuestions();
	}
	
	public boolean updateQuestion(Question change) {
		return dbRepo.updateQuestion(change);
	}
	
	public boolean deleteQuestion(int id) {
		return dbRepo.deleteQuestion(id);
	}
	
	
	//Answers
	public int addAnswer(Answer a) {
		return dbRepo.addAnswer(a);
	}
	
	public Answer getAnswer(int id) {
		return dbRepo.getAnswer(id);
	}
	
	public List<Answer> getAllAnswers(int id) {
		return dbRepo.getAllAnswers(id);
	}
	
	public boolean updateAnswer(Answer change) {
		return dbRepo.updateAnswer(change);
	}
	
	public boolean deleteAnswer(int id) {
		return dbRepo.deleteAnswer(id);
	}
	
	// Progress
	public int addProgress(Progress a) {
		return dbRepo.addProgress(a);
	}
	
	public Progress getProgress(int id) {
		return dbRepo.getProgress(id);
	}
	
	public List<Progress> getAllProgress(int id) {
		return dbRepo.getAllProgress(id);
	}
	
	public boolean updateProgress(Progress change) {
		return dbRepo.updateProgress(change);
	}
	
	public boolean deleteProgress(int id) {
		return dbRepo.deleteProgress(id);
	}
	
	
	
	//Permissions
	public int addPermission(Permission a) {
		return dbRepo.addPermission(a);
	}
	
	public Permission getPermission(int id) {
		return dbRepo.getPermission(id);
	}
	
	public List<Permission> getAllPermissions() {
		return dbRepo.getAllPermissions();
	}
	
	public List<Permission> getAllPermissions(int id){
		return dbRepo.getAllPermissions(id);
	}
	
	public boolean updatePermission(Permission change) {
		return dbRepo.updatePermission(change);
	}
	
	public boolean deletePermission(int id) {
		return dbRepo.deletePermission(id);
	}
	
}
