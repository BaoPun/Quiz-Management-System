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
import com.project2.demo.beans.NewQuiz;
import com.project2.demo.beans.Progress;
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
      
		System.out.println("Attempting to log in");
      
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
	
	@PostMapping(path="/register", consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE}) 
	public ResponseEntity<String> register(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) { 
		
		String username = paramMap.getFirst("username");
		String password = paramMap.getFirst("password");
		String teacher = paramMap.getFirst("teacher");
		HttpHeaders headers=new HttpHeaders();
		
		// Attempt to register 
		if(engine.register(username, password, Integer.parseInt(teacher.split(" ")[0])))
			headers.setLocation(URI.create("/"));
		else
			headers.setLocation(URI.create("/register"));
		
		
		return new ResponseEntity<String>(headers,HttpStatus.FOUND);
		
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
	public List<Progress> getUserQuizResults(@RequestParam String user, @RequestParam String quiz) {
		int userID = Integer.parseInt(user);
		int quizID = Integer.parseInt(quiz);
		return engine.getProgressForUserAndQuiz(quizID,userID);
	}

	@GetMapping(value="/s/getQuestions", produces="application/json")
	public List<Question> getQuizQuestions(@RequestParam String quizid) {
		return engine.getQuizQuestions(Integer.parseInt(quizid));
	}
	
	@GetMapping(value="/s/getPossibleAnswers", produces="application/json")
	public List<Answer> getPossibleAnswers(@RequestParam String questionid) {
		return engine.getQuestionAnswers(Integer.parseInt(questionid));
	}
	
	@PostMapping(path="/s/submitNewQuiz", consumes= "application/json")
	public String login_page(@RequestBody NewQuiz quiz) {
		//TODO fill in
		return "success";
	}
	
	public class SingleQuestion {
		public String description;
		public List<Answer> answers;
	}
	
	@GetMapping(value="/s/getSingleQuestion", produces="application/json")
	public SingleQuestion getSingleQuestion(@RequestParam String questionid) {
		SingleQuestion retval = new SingleQuestion();
		int questionidNum = Integer.parseInt(questionid);
		
		List<Answer> questionAnswers = engine.getQuestionAnswers(questionidNum);
		retval.answers=questionAnswers;
		
		Question question = engine.getQuestion(questionidNum);
		retval.description=question.getDescription();
		return retval;
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
	
	@PostMapping(value="/s/endQuiz",
			consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
			produces="application/json")
	public Timetable endQuiz(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {
		int quizID = Integer.parseInt(paramMap.getFirst("quizID"));
		int userID = engine.getLoggedInUser(session.getId()).getId();
		return engine.endQuiz(quizID, userID);
	}
}
