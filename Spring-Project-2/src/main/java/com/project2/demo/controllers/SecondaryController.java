package com.project2.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;

@RestController
public class SecondaryController {
 
	public SecondaryController() {
	}
	

	@Autowired
	private Engine engine;
	
	@PostMapping(path="/login", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> login_page(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {

		String username=paramMap.getFirst("username");
		String password=paramMap.getFirst("password");
		HttpHeaders headers=new HttpHeaders();
		
		if (engine.login(session.getId(),username,password)) {
			headers.setLocation(URI.create("/s/teacher"));
			return new ResponseEntity<String>(headers,HttpStatus.FOUND);
		} else {
			headers.setLocation(URI.create("/"));
			return new ResponseEntity<String>(headers,HttpStatus.FOUND);
		}
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
	
	@GetMapping(value="/s/getUserQuizResults", produces="application/json")
	public List<Answer> getUserQuizResults(@RequestParam String user, @RequestParam String quiz) {
		int userID = Integer.parseInt(user);
		int quizzID = Integer.parseInt(quiz);
		List<Progress> results = engine.getProgressForUserAndQuiz(userID, quizzID);
		List<Answer> retval = new ArrayList<Answer>();
		for (Progress p : results) {
			retval.add(p.getAnswer());
		}
		return retval;
	}



}
