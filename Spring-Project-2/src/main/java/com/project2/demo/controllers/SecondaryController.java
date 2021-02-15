package com.project2.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.beans.User;
import com.project2.demo.services.DBService;

@RestController
public class SecondaryController {

	@Autowired
	private DBService services;
	
	public SecondaryController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(value="/thing", produces = "application/json")
	public User gettest() {
		User user = services.getUser("bim");
		List<User> users = services.getAllUsers();
		
		for (User u : users) {
			System.out.println(u.getUsername());
		}
//		User user2 = new User();
//		user2.setUsername("baraz");
//		user2.setTeacher(user);
//		user2.setRole(UserType.STUDENT);
//		user2.setPasswordHash("");
//		services.addThing(user2);
		System.out.println(user.getId());
		//System.out.println(services.getQuiz(4));
		System.out.println(System.identityHashCode(user));
		//System.out.println(System.identityHashCode(user.getTeacher()));
		//System.out.println(System.identityHashCode(user.getTeacher().getTeacher()));
		//System.out.println(System.identityHashCode(user.getTeacher().getTeacher().getTeacher()));
		return null;
	}

}
