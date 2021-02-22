package com.project2.demo.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.CompletedQuiz;
import com.project2.demo.beans.NewQuiz;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.Timetable;
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
      
		System.out.println("Attempting to log in: {" + username + ", " + password + "}");
		
      
		// If login was successful
    	if (engine.login(session.getId(),username,password)) {
			if(engine.isTeacherLoggedIn(session.getId()))
				headers.setLocation(URI.create("/s/teacher"));
			else
				headers.setLocation(URI.create("/s/student"));
		}
		// Invalid login
		else 
			headers.setLocation(URI.create("/"));
    	
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }
	
	@GetMapping(path="/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(URI.create("/"));
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}
	
	@PostMapping(path="/register", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE}) 
	public ResponseEntity<String> register(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) { 
		
		String username = paramMap.getFirst("username");
		String password = paramMap.getFirst("password");
		String teacher = paramMap.getFirst("teacher");
		HttpHeaders headers=new HttpHeaders();
		
		// Attempt to register 
		if(engine.register(username, password, Integer.parseInt(teacher.split(" ")[0])))
			headers.setLocation(URI.create("/registerSuccess"));
		else
			headers.setLocation(URI.create("/registerFailure"));
		
		
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);	
	}
	
	
}
