package com.project2.demo.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.demo.beans.CompletedQuiz;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.User;
import com.project2.demo.entities.Engine;


// No RestController annotation because our returning String would display an empty page in an HTML with that String
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
  
	// Mapped to student.html located under src/main/resources/templates/s
	@GetMapping("/student")
	public String student_page(Model model, HttpSession session) {
		
		// Map out the first div id
		User loggedStudent = engine.getLoggedInUser(session.getId());
		if(loggedStudent != null)
			model.addAttribute("student", loggedStudent.getUsername());
		else {
			model.addAttribute("student", null);
			return "s/student";
		}
			
		// Map out the list of quizzes
		List<Quiz> listOfQuizzes = engine.getQuizzesFromUser(loggedStudent.getTeacher());
	 
		List<String> quizNames = null;
		List<Integer> quizIDs = null;
		
		if(listOfQuizzes != null) {
			
			quizNames = new ArrayList<String>();
			quizIDs = new ArrayList<Integer>();
			
			for(Quiz q : listOfQuizzes) {
				quizNames.add(q.getName());
				quizIDs.add(q.getId());
			}
		}
		
		model.addAttribute("quizzes", quizNames);
		model.addAttribute("quizIDs", quizIDs);
		
		return "s/student";
	}

	// Mapped to teacher.html located under src/main/resources/templates/s
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
	
	// Mapped to student_grade.html located under src/main/resources/templates/s
	@GetMapping("/studentGrades")
	public String student_grade_page(Model model, HttpSession session) {
		
		int studentid = engine.getLoggedInUser(session.getId()).getId();
		List<Quiz> quizzes = engine.getQuizzesStartedByStudent(studentid);
		
		List<String> quizNames = new ArrayList<String>();
		List<String> quizScores = new ArrayList<String>();
		for (Quiz q : quizzes) {
			CompletedQuiz cquiz = engine.getQuizResults(q.getId(), studentid);
			quizNames.add(q.getName());
			DecimalFormat df = new DecimalFormat("00"); 
			
			quizScores.add("%"+df.format(cquiz.getScore()));
		}
		model.addAttribute("quizNames",quizNames);
		model.addAttribute("quizScores",quizScores);
		
		User getUser = engine.getLoggedInUser(session.getId());
		if(getUser != null)
			model.addAttribute("student", getUser.getUsername());
		else
			model.addAttribute("student", null);
		return "s/student_grade";
	}
}
