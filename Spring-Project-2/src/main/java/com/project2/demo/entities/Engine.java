package com.project2.demo.entities;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.project2.demo.DAO.UserRepository;
import com.project2.demo.beans.User;
import com.project2.demo.util.Password;

public class Engine {
	
	private Map<String,User> loggedInUsers;
	
	@Autowired
	private UserRepository repository;
	
	public Engine() {
		super();
		loggedInUsers = new HashMap<String,User>();
	}

	public boolean login(String sessionID, String username, String password) {
		User user = repository.getUserByName(username);
		if (user == null) {
			return false;
		}
		String hash = Password.hash(password);
		return user.getPasswordHash().equals(hash);
	}
	
	public User getLoggedInUser(String sessionID) {
		return loggedInUsers.get(sessionID);
	}

}
