package com.project2.demo.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;

@RestController
public class SecondaryController {
 
	public SecondaryController() {}
	
	@Autowired
	private Engine engine;
	
	@PostMapping(path="/login", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> login_page(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {

		String username=paramMap.getFirst("username");
		String password=paramMap.getFirst("password");
		HttpHeaders headers=new HttpHeaders();
		
		System.out.println("Attempting to login");
		
		if(username.equals("register") && password.equals(""))
			headers.setLocation(URI.create("/register"));
		else if (engine.login(session.getId(),username,password)) 
			headers.setLocation(URI.create("/s/teacher"));
		else 
			headers.setLocation(URI.create("/"));
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }
	
	@PostMapping(path="/register", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE}) 
	public ResponseEntity<String> register(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) { 
		
		String username = paramMap.getFirst("username");
		String password = paramMap.getFirst("password");
		String teacher = paramMap.getFirst("teacher");
		HttpHeaders headers=new HttpHeaders();
		
		System.out.println(paramMap);
		System.out.println("Attempting to register: " + teacher);
		
		// Attempt to register 
		if(engine.register(username, password, Integer.parseInt(teacher.split(" ")[0])))
			headers.setLocation(URI.create("/"));
		else
			headers.setLocation(URI.create("/register"));
		
		
		return new ResponseEntity<String>(headers,HttpStatus.FOUND);
		
	}
	
	@GetMapping(value="/s/getMyQuizzes", produces="application/json")
	public List<Quiz> getMyQuizzes(HttpSession session) {
		User teacher=engine.getLoggedInUser(session.getId());
		return engine.getQuizzesFromUser(teacher);
	}
	
	@GetMapping(value="/s/getQuizzes", produces="application/json")
	public List<Quiz> getQuizzes(@RequestParam String teacher) {
		return engine.getQuizzesFromUser(engine.getUserByName(teacher));
	}



}
