package com.project2.demo.entities;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.project2.demo.beans.User;
import com.project2.demo.services.DBService;
import com.project2.demo.util.Password;

public class Engine {

	private static Engine globalEngine = new Engine();
	
	private Map<String,User> loggedInUsers;
	
	@Autowired
	private DBService services;
	//private UserRepository repository;
	
	private Engine() {
	}
	
	public static Engine getGlobalEngine() {
		return globalEngine;
	}
	
	public boolean login(String sessionID, String username, String password) {
		User user = services.getUser(username);
		if (user == null) {
			return false;
		}
		String hash = Password.hash(password);
		return user.getPasswordHash()==hash;
	}
	
	public User getLoggedInUser(String sessionID) {
		return loggedInUsers.get(sessionID);
	}

}
