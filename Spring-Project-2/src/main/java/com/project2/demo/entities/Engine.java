package com.project2.demo.entities;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.project2.demo.DAO.DBRepo;
import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;
import com.project2.demo.util.Password;



public class Engine {
	
	// Nested class to contain a description of what type of User has logged in.
	// Contains 2 fields: their id and their role type.
	private class LoggedInType{
		private int loggedId;
		private UserType role;
		
		public LoggedInType() {
			this.loggedId = 0;
			this.role = null;
		}

		public int getLoggedId() {
			return loggedId;
		}

		private void setLoggedId(int loggedId) {
			this.loggedId = loggedId;
		}

		public UserType getRole() {
			return role;
		}

		private void setRole(UserType role) {
			this.role = role;
		}
		
		public void setFields(int loggedId, UserType role) {
			this.setLoggedId(loggedId);
			this.setRole(role);
		}
		
	}
	
	private Map<String,User> loggedInUsers;
	private LoggedInType userType;
	
	@Autowired
	private DBRepo services;

	
	public Engine() {
		super();
		loggedInUsers = new HashMap<String,User>();
		userType = new LoggedInType();
	}
	
	// Is the current logged in user a teacher?
	public boolean isTeacherLoggedIn() {
		return (this.userType.role == UserType.TEACHER ? true : false);
	}
	
	public List<User> getAllUsers() {
		return services.getAllUsers();
	}
	
	public List<User> getAllStudents(){
		// Only return a list if the current User is a teacher.  
		return (userType.getRole() == UserType.TEACHER ? services.getAllStudents(services.getUser(userType.getLoggedId())) : null);
	}
	
	public List<User> getAllTeachers() {
		return services.getAllTeachers();
	}
	
	public List<Quiz> getQuizzesFromUser(User user) {
		return services.getQuizzesFromUser(user.getId());
	}

	public User getUserByName(String username) {
		return services.getUser(username);
	}
  
  public List<Progress> getProgressForUserAndQuiz(int userID,int quizID) {
		return services.getProgressForUserAndQuiz(userID, quizID);
	}
	
	public List<Question> getQuizQuestions(int quizid) {
		return services.getQuizQuestions(quizid);
	}
	
	public List<Answer> getQuestionAnswers(int questionid) {
		return services.getQuestionAnswers(questionid);
	}
	
	
	public boolean login(String sessionID, String username, String password) {
		User user = services.getUser(username);
		if (user == null) 
			return false;
		String hash = Password.hash(password);
		boolean matches=user.getPasswordHash().equals(hash);
		if (matches) {
			loggedInUsers.put(sessionID, user);
			if(user.getRole() == UserType.TEACHER) 
				this.userType.setFields(user.getId(), UserType.TEACHER);
			else if(user.getRole() == UserType.STUDENT)
				this.userType.setFields(user.getId(), UserType.STUDENT);
			else
				this.userType.setFields(user.getId(), UserType.ADMIN);
		}
		return matches;
	}
	
	public boolean register(String username, String password, int teacherId) {
		User newStudent = new User(username, Password.hash(password), UserType.STUDENT, services.getUser(teacherId));
		boolean success = services.addUser(newStudent) > -1;
		if(success)
			System.out.println("Successfully added student: " + newStudent);
		else
			System.out.println("FAILURE");
		return success;
	}
	
	public User getLoggedInUser(String sessionID) {
		return loggedInUsers.get(sessionID);
	}
	
	public boolean isSessionLoggedIn(String sessionID) {
		return loggedInUsers.containsKey(sessionID);
	}
	
	public List<User> getMyStudents(String sessionID) {
		int teacherID = loggedInUsers.get(sessionID).getId();
		return getUserStudents(teacherID);
	}
	
	public List<User> getUserStudents(int teacherID) {
		return services.getUserStudents(teacherID);
	}
	
	public boolean isPermittedPage(String sessionID, URI uri) {
		User user = loggedInUsers.get(sessionID);
		String uriStr = uri.normalize().toASCIIString();
		
		if (user == null) {
			return !uriStr.matches("/s/.*");
		}
		switch (user.getRole()) {
			case ADMIN:
				return true;
			case STUDENT:
				return isStudentPage(uriStr);
			case TEACHER:
				return isTeacherPage(uriStr);
		}
		
		return true;
	}

	public boolean isStudentPage(String uri) {
		if (uri.matches("/s/teacher.html.*")) {
			return false;
		}
		if (uri.matches("/s/fragments.html.*")) {
			return false;
		}
		return true;
	}

	public boolean isTeacherPage(String uri) {
		return true;
	}
	
}


