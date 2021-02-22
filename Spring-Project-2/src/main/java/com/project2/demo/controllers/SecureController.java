package com.project2.demo.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.CompletedQuiz;
import com.project2.demo.beans.NewQuiz;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.QuestionAnswerPair;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.Timetable;
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
	public String question_page(Model model,@RequestParam int quizid) {
		List<QuestionAnswerPair> pairs = engine.getQuizPairs(quizid);
		System.out.println(quizid);
		
		List<List<String>> answers = new ArrayList<List<String>> ();
		List<String> questions = new ArrayList<String>();
		for (int i=0;i<pairs.size();++i) {
			QuestionAnswerPair pair = pairs.get(i);
			System.out.println(pair);
			pair.sortAnswers();
			List<String> answerRow = new ArrayList<String>();
			for (int j=0;j<pair.getAnswers().size();++j) {
				answerRow.add(pair.getAnswers().get(j).getAnswerText());
			}
			answers.add(answerRow);
			questions.add(pair.getQuestion().getDescription());
		}
		
		model.addAttribute("answers",answers);
		model.addAttribute("questions",questions);
		
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
	
	/*
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
	
	@GetMapping(value="/getQuizzesStartedByStudent", produces="application/json")
	public List<Quiz> getQuizzesStartedByStudent(@RequestParam String userid) {
		return engine.getQuizzesStartedByStudent(Integer.parseInt(userid));
	}
	
	@GetMapping(value="/getMyQuizzes", produces="application/json")
	public List<Quiz> getMyQuizzes(HttpSession session) {
		User teacher=engine.getLoggedInUser(session.getId());
		return engine.getQuizzesFromUser(teacher);
	}
	
	@GetMapping(value="/s/getQuizzes", produces="application/json")
	public List<Quiz> getQuizzes(@RequestParam String teacher) {
		return engine.getQuizzesFromUser(engine.getUserByName(teacher));
	}
	
	@GetMapping(value="/getUserQuizResults", produces="application/json")
	public CompletedQuiz getUserQuizResults(@RequestParam String user, @RequestParam String quiz) {
		int userID = Integer.parseInt(user);
		int quizID = Integer.parseInt(quiz);
		return engine.getQuizResults(quizID,userID);
	}

	@GetMapping(value="/getQuestions", produces="application/json")
	public List<Question> getQuizQuestions(@RequestParam String quizId) {
		return engine.getQuizQuestions(Integer.parseInt(quizId));
	}
	
	@GetMapping(value="/getPossibleAnswers", produces="application/json")
	public List<Answer> getPossibleAnswers(@RequestParam String questionId) {
		return engine.getQuestionAnswers(Integer.parseInt(questionId));
	}
	
	@PostMapping(path="/submitNewQuiz", consumes= "application/json")
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
	
	@GetMapping(value="/getSingleQuestion", produces="application/json")
	public SingleQuestion getSingleQuestion(@RequestParam String questionid) {
		int questionidNum = Integer.parseInt(questionid);
		
		List<Answer> questionAnswers = engine.getQuestionAnswers(questionidNum);
		Question question = engine.getQuestion(questionidNum);
		
		return new SingleQuestion(questionAnswers, question.getDescription());
	}
	
	@GetMapping(value="/getQuizTimetable", produces="application/json")
	public Timetable getQuizTimetable(@RequestParam String quizid, @RequestParam String userid) {
		int quizidNum = Integer.parseInt(quizid);
		int useridNum = Integer.parseInt(userid);
		return engine.getTimetable(quizidNum, useridNum);
	}
	
	@PostMapping(value="/beginQuiz",
			consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String beginQuiz(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {
		int quizID = Integer.parseInt(paramMap.getFirst("quizID"));
		int userID = engine.getLoggedInUser(session.getId()).getId();
		engine.startQuiz(quizID, userID);
		return "OK";
	}
	
	@PostMapping(value="/endQuiz",
			consumes= {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
			produces="application/json")
	public Timetable endQuiz(HttpSession session, @RequestParam MultiValueMap<String,String> paramMap) {
		int quizID = Integer.parseInt(paramMap.getFirst("quizID"));
		int userID = engine.getLoggedInUser(session.getId()).getId();
		return engine.endQuiz(quizID, userID);
	}
	*/
}
