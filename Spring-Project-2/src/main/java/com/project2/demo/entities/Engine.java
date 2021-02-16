package com.project2.demo.entities;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.services.DBService;
import com.project2.demo.util.Password;

public class Engine {
	
	private Map<String,User> loggedInUsers;

	@Autowired
	private DBService services;
	
	public Engine() {
		super();
		loggedInUsers = new HashMap<String,User>();
	}
	
	public List<User> getAllUsers() {
		return services.getAllUsers();
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
	
	public boolean login(String sessionID, String username, String password) {
		User user = services.getUser(username);
		if (user == null) {
			return false;
		}
		String hash = Password.hash(password);
		boolean matches=user.getPasswordHash().equals(hash);
		if (matches) {
			loggedInUsers.put(sessionID, user);
		}
		return matches;
	}
	
	public User getLoggedInUser(String sessionID) {
		return loggedInUsers.get(sessionID);
	}
	
	public boolean isSessionLoggedIn(String sessionID) {
		return loggedInUsers.containsKey(sessionID);
	}
	
	public boolean isPermittedPage(String sessionID, URI uri) {
		User user = loggedInUsers.get(sessionID);
		String uriStr = uri.normalize().toASCIIString();
		
		if (user != null) {
			System.out.println(user.getUsername());
			System.out.println(user.getRole());
		}
		System.out.println(uriStr);
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


