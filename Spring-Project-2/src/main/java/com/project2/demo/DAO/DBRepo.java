package com.project2.demo.DAO;

import java.util.List;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Permission;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;

public interface DBRepo {
	
	//User Interfaces
	public int addUser(User a);
	public User getUser(int id);
	public User getUserByName(String user);
	public List<User> getAllUsers();
	public boolean updateUser(User change);
	public boolean deleteUser(int id);
	public List<User> getAllStudents(int id);
	
	//Quizzes Interfaces
	public int addQuiz(Quiz a);
	public Quiz getQuiz(int id);
	public List<Quiz> getAllQuizzes();
	public boolean updateQuiz(Quiz change);
	public boolean deleteQuiz(int id);

	
	//Permissions
	public Permission addPermission(Permission a);
	public Permission getPermission(int id);
	public List<Permission> getAllPermission();
	public Permission updatePermission(Permission change);
	public boolean deletePermission(int id);
		
	
	//Questions
	public Question addQuestion(Question a);
	public Question getQuestion(int id);
	public List<Question> getAllQuestions();
	public Question updateQuestion(Question change);
	public boolean deleteQuestion(int id);
	
	
	//Answers
	public Answer addAnswer(Answer a);
	public Answer getAnswer(int id);
	public Answer updateAnswer(Answer change);
	public boolean deleteAnswer(int id);
	public List<Answer> getAllAnswer();
	
	// Progress
	public Progress addProgress(Progress a);
	public Progress getProgress(int id);
	public List<Progress> getAllProgress();
	public Progress updateProgress(Progress change);
	public boolean deleteProgress(int id);
	
}
