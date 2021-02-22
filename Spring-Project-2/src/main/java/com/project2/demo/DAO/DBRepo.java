package com.project2.demo.DAO;

import java.io.Serializable;
import java.util.List;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Permission;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.Timetable;
import com.project2.demo.beans.User;

public interface DBRepo{
	
	//Users
	int addUser(User a);
	boolean isUniqueUser(String user);
	User getUser(int id);
	User getUser(String user);
	List<User> getAllUsers();
	List<User> getAllStudents(User teacher);
	boolean updateUser(User change);
	boolean deleteUser(int id);
	List<User> getAllTeachers();
	List<User> getUserStudents(int teacherid);
	
	//Quizzes 
	int addQuiz(Quiz a);
	Quiz getQuiz(int id);
	List<Quiz> getQuizzes(String name);
	List<Quiz> getAllQuizzes();
	boolean updateQuiz(Quiz change);
	boolean deleteQuiz(int id);
	List<Quiz> getQuizzesFromUser(int id);
	List<Quiz> getQuizzesStartedByStudent(int studentid);
	
	//Questions
	int addQuestion(Question a);
	Question getQuestion(int id);
	List<Question> getAllQuestions();
	boolean updateQuestion(Question change);
	boolean deleteQuestion(int id);
	List<Question> getQuizQuestions(int quizid);
	
	//Answers
	int addAnswer(Answer a);
	Answer getAnswer(int id);
	List<Answer> getAllAnswers(int id);
	boolean updateAnswer(Answer change);
	boolean deleteAnswer(int id);
	List<Answer> getQuestionAnswers(int questionid);
	List<Answer> getQuizAnswers(int quizid);
	
	// Progress
	int addProgress(Progress a);
	Progress getProgress(int id);
	List<Progress> getAllProgress(int id);
	boolean updateProgress(Progress change);
	boolean deleteProgress(int id);
	List<Progress> getProgressForUserAndQuiz(int quizid, int userid);
	
	
	//Permissions
	int addPermission(Permission a);
	Permission getPermission(int id);
	List<Permission> getAllPermissions();
	List<Permission> getAllPermissions(int id);
	boolean updatePermission(Permission change);
	boolean deletePermission(int id);
	
	//Timetable
	Integer addTimetable(Timetable table);
	Timetable getTimetable(int id);
	List<Timetable> getAllTimetables();
	boolean updateTimetable(Timetable change);
	List<Timetable> getTimetables(int quizID,int userID);
	
	
}
