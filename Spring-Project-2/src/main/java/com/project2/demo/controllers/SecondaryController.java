package com.project2.demo.controllers;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import com.project2.demo.beans.UserSubmittedProgress;
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
	
	

	
	@GetMapping(value="/s/getQuizzesStartedByStudent", produces="application/json")
	public List<Quiz> getQuizzesStartedByStudent(@RequestParam String userid) {
		return engine.getQuizzesStartedByStudent(Integer.parseInt(userid));
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
	public CompletedQuiz getUserQuizResults(@RequestParam String user, @RequestParam String quiz) {
		int userID = Integer.parseInt(user);
		int quizID = Integer.parseInt(quiz);
		return engine.getQuizResults(quizID,userID);
	}

	@GetMapping(value="/s/getQuestions", produces="application/json")
	public List<Question> getQuizQuestions(@RequestParam String quizId) {
		return engine.getQuizQuestions(Integer.parseInt(quizId));
	}
	
	@GetMapping(value="/s/getPossibleAnswers", produces="application/json")
	public List<Answer> getPossibleAnswers(@RequestParam String questionId) {
		return engine.getQuestionAnswers(Integer.parseInt(questionId));
	}
	
	@PostMapping(path="/s/submitNewQuiz", consumes= "application/json")
	public String login_page(HttpSession session, @RequestBody NewQuiz quiz) {
		engine.makeNewQuiz(session.getId(), quiz);
		return "success";
	}
	
	private class SingleQuestion {
		public String description;
		public List<Answer> answers;

		public SingleQuestion(List<Answer> answers, String description) {
			this.answers = answers;
			this.description = description;
		}
	}
	
	@GetMapping(value="/s/getSingleQuestion", produces="application/json")
	public SingleQuestion getSingleQuestion(@RequestParam String questionid) {
		int questionidNum = Integer.parseInt(questionid);
		
		List<Answer> questionAnswers = engine.getQuestionAnswers(questionidNum);
		Question question = engine.getQuestion(questionidNum);
		
		return new SingleQuestion(questionAnswers, question.getDescription());
	}
	
	@GetMapping(value="/s/getQuizTimetable", produces="application/json")
	public Timetable getQuizTimetable(@RequestParam String quizid, @RequestParam String userid) {
		int quizidNum = Integer.parseInt(quizid);
		int useridNum = Integer.parseInt(userid);
		return engine.getTimetable(quizidNum, useridNum);
	}
	
	@PostMapping(value="/s/beginQuiz",
			consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String beginQuiz(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {
		int quizID = Integer.parseInt(paramMap.getFirst("quizID"));
		int userID = engine.getLoggedInUser(session.getId()).getId();
		engine.startQuiz(quizID, userID);
		return "OK";
	}
	
	@PostMapping(value="/s/submitQuiz",consumes="application/json")
	public String submitQuiz(HttpSession session, @RequestBody List<UserSubmittedProgress> answers) {
		System.out.println(answers);
		return "OK";
	}
	
	@PostMapping(value="/s/endQuiz",
			consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
			produces="application/json")
	public Timetable endQuiz(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {
		int quizID = Integer.parseInt(paramMap.getFirst("quizID"));
		int userID = engine.getLoggedInUser(session.getId()).getId();
		return engine.endQuiz(quizID, userID);
	}
	
	
}
