package com.project2.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;


//@RestController
@Controller
@RequestMapping("/s")
public class SecureController {
	
	@Autowired
	private Engine engine;

	public SecureController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/grade")
	public String grade_page(Model model) {
		return "s/grade";
	}

	@GetMapping("/question")
	public String question_page(Model model) {
		return "s/question";
	}

	@GetMapping("/quiz_selector")
	public String quiz_selector_page(Model model) {
		return "s/quiz_selector";
	}
  
	@GetMapping("/quiz_generator")
	public String quiz_generator_page(Model model) {
		return "s/quiz_generator";
	}
  
	@GetMapping("/student")
	public String student_page(Model model, HttpSession session) {
		
		// Map out the first div id
		User loggedStudent = engine.getLoggedInUser(session.getId());
		if(loggedStudent != null)
			model.addAttribute("student", loggedStudent.getUsername());
		else
			model.addAttribute("student", null);
		
		// Map out the list of quizzes
		List<Quiz> listOfQuizzes = engine.getQuizzesFromUser(loggedStudent.getTeacher());
	 
		List<String> quizNames = new ArrayList<String>();
		List<Integer> quizIDs = new ArrayList<Integer>();
		
		if(listOfQuizzes != null) {
			for(Quiz q : listOfQuizzes) {
				quizNames.add(q.getName());
				quizIDs.add(q.getId());
			}
		}
		
		model.addAttribute("quizzes", quizNames);
		model.addAttribute("quizIDs", quizIDs);
		
		return "s/student";
	}

	@GetMapping("/teacher")
	public String teacher_page(Model model, HttpSession session) {
    
		String sessionID = session.getId();
		List<User> users = engine.getMyStudents(sessionID);
    
		List<String> userNames = new ArrayList<String>();
		List<Integer> userIDs= new ArrayList<Integer>();
		if(users != null) {
			for (User u: users) {
				userNames.add(u.getUsername());
				userIDs.add(u.getId());
			}
		}

		User loggedTeacher = engine.getLoggedInUser(sessionID);
		if(loggedTeacher != null)
			model.addAttribute("teacher", loggedTeacher.getUsername());
		else
			model.addAttribute("teacher", null);
		model.addAttribute("students", userNames);
		model.addAttribute("studentIDs", userIDs);
		return "s/teacher";
	}
}
