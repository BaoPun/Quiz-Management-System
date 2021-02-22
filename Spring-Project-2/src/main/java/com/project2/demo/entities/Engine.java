package com.project2.demo.entities;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.project2.demo.DAO.DBRepo;
import com.project2.demo.beans.Answer;
import com.project2.demo.beans.CompletedQuiz;
import com.project2.demo.beans.NewQuiz;
import com.project2.demo.beans.Progress;
import com.project2.demo.beans.Question;
import com.project2.demo.beans.QuestionAnswerPair;
import com.project2.demo.beans.Quiz;
import com.project2.demo.beans.Timetable;
import com.project2.demo.beans.User;
import com.project2.demo.beans.UserType;
import com.project2.demo.util.Password;



public class Engine {
	
	private Map<String,User> loggedInUsers;
	
	@Autowired
	private DBRepo services;

	
	public Engine() {
		super();
		loggedInUsers = new HashMap<String,User>();
	}
	
	// Called once the User attempts to login
	public boolean login(String sessionID, String username, String password) {
		User user = services.getUser(username);
		if (user == null) 
			return false;
		String hash = Password.hash(password);
		boolean matches=user.getPasswordHash().equals(hash);
		if (matches) 
			loggedInUsers.put(sessionID, user);
		return matches;
	}
	
	// Called once the User attempts to register a new account
	public boolean register(String username, String password, int teacherId) {
		User newStudent = new User(username, Password.hash(password), UserType.STUDENT, services.getUser(teacherId));
		return services.addUser(newStudent) > -1;
	}
	
	// Logging out of the user, remove the mapping
	public void removeLoggedUser(String sessionID) {
		if(loggedInUsers.size() > 0)
			loggedInUsers.remove(sessionID);
	}
	
	
	// Is the current logged in user a teacher?
	public boolean isTeacherLoggedIn(String sessionID) {
		return (this.loggedInUsers.get(sessionID).getRole() == UserType.TEACHER ? true : false);
	}
	
	/*
	// Getting every single User from the DB.  Not sure if this is used.
	public List<User> getAllUsers() {
		return services.getAllUsers();
	}
	*/
	
	// Get all Teachers registered in the DB (for use with registering a new Student)
	public List<User> getAllTeachers() {
		return services.getAllTeachers();
	}
	
	// Get all students that belong to a specific teacher.
	public List<User> getUserStudents(int teacherID) {
		return services.getUserStudents(teacherID);
	}
	
	// Get a list of quizzes that belong to either a Student or a Teacher
	public List<Quiz> getQuizzesFromUser(User user) {
		return services.getQuizzesFromUser(user.getId());
	}
	
	// Get a list of Quizzes that have been started by a specific student
	public List<Quiz> getQuizzesStartedByStudent(int studentid) {
		return services.getQuizzesStartedByStudent(studentid);
	}
	
	// Retrieve a specific User by their name.
	public User getUserByName(String username) {
		return services.getUser(username);
	}
  
	
	public CompletedQuiz getQuizResults(int quizid,int userid) {
		List<Progress> progress = services.getProgressForUserAndQuiz(quizid, userid);
		Set<Integer> chosenAnswers = new HashSet<Integer>();
		Set<Question> wrongQuestions = new HashSet<Question>();
		
		for (Progress p : progress) {
			chosenAnswers.add(p.getAnswer().getId());
			if (!p.getAnswer().getIsCorrect()) {
				wrongQuestions.add(p.getAnswer().getQuestion());
			}
		}
		
		Map<Question,QuestionAnswerPair> map = new HashMap<Question,QuestionAnswerPair>();
		

		List<Answer> answers = services.getQuizAnswers(quizid);
		for (Answer a : answers) {
			if (!map.containsKey(a.getQuestion())) {
				QuestionAnswerPair pair = new QuestionAnswerPair(a.getQuestion(),new ArrayList<Answer>());
				pair.getAnswers().add(a);
				map.put(a.getQuestion(), pair);
			} else {
				QuestionAnswerPair pair = map.get(a.getQuestion());
				pair.getAnswers().add(a);
			}
			if (a.getIsCorrect()) {
				if (!chosenAnswers.contains(a.getId())) {
					wrongQuestions.add(a.getQuestion());
				}
			}
		}
		
		List<Question> questions = services.getQuizQuestions(quizid);

		
		double score = (1-(((double)wrongQuestions.size()) / ((double)questions.size())))*100.0;
		
		return new CompletedQuiz(progress, score, new HashSet<QuestionAnswerPair>(map.values()));
	}
	
	public Question getQuestion(int questionid) {
		return services.getQuestion(questionid);
	}
	
	public Timetable getTimetable(int quizID, int userID) {
		return services.getTimetables(quizID, userID).get(0);
	}
	
	public int startQuiz(int quizID, int userID) {
		Timetable table = new Timetable();
		table.setQuizStartTime(Timestamp.valueOf(LocalDateTime.now()));
		table.setQuizID(quizID);
		table.setUserID(userID);
		return services.addTimetable(table);
	}
	
	public Timetable endQuiz(int quizID, int userID) {
		Timetable table = this.getTimetable(quizID, userID);
		if (table == null) {
			return null;
		}
		table.setQuizEndTime(Timestamp.valueOf(LocalDateTime.now()));
		services.updateTimetable(table);
		return table;
	}
	
	public Quiz makeNewQuiz(String sessionid, NewQuiz newQuiz) {
		User currentUser = loggedInUsers.get(sessionid);
		Quiz quiz = new Quiz();
		quiz.setName(newQuiz.getName());
		quiz.setUser(currentUser);
		services.addQuiz(quiz);
		
		List<String> questionStrs = newQuiz.getQuestions();
		List<List<String>> answerStrs = newQuiz.getAnswers();
		List<List<Boolean>> answerCorrectness = newQuiz.getAnswerCorrectness();
		for (int i=0;i<questionStrs.size();++i) {
			Question question = new Question();
			question.setDescription(questionStrs.get(i));
			question.setOrdering(i);
			question.setQuestionType("MULTIPLE CHOICE");
			question.setQuiz(quiz);
			services.addQuestion(question);
			
			for (int j=0;j<answerStrs.get(i).size();++j) {
				Answer answer = new Answer();
				answer.setAnswerText(answerStrs.get(i).get(j));
				answer.setIsCorrect(answerCorrectness.get(i).get(j));
				answer.setOrdering(j);
				answer.setQuestion(question);
				services.addAnswer(answer);
			}
		}
		
		return quiz;
	}
	
	public List<Question> getQuizQuestions(int quizid) {
		return services.getQuizQuestions(quizid);
	}
	
	public List<Answer> getQuestionAnswers(int questionid) {
		return services.getQuestionAnswers(questionid);
	}
	
	public List<QuestionAnswerPair> getQuizPairs(int quizid) {
		List<Question> questions = this.getQuizQuestions(quizid);
		List<QuestionAnswerPair> results = new ArrayList<QuestionAnswerPair>();
		for (int i=0;i<questions.size();++i) {
			QuestionAnswerPair pair = new QuestionAnswerPair();
			pair.setQuestion(questions.get(i));
			pair.setAnswers(this.getQuestionAnswers(questions.get(i).getId()));
			results.add(pair);
		}
		return results;
	}
	
	public User getLoggedInUser(String sessionID) {
		return loggedInUsers.get(sessionID);
	}
	
	public boolean isSessionLoggedIn(String sessionID) {
		return loggedInUsers.containsKey(sessionID);
	}
	
	public List<User> getMyStudents(String sessionID) {
		User getTeacher = loggedInUsers.get(sessionID);
		int teacherID = Integer.MIN_VALUE;
		if(getTeacher != null) {
			teacherID = getTeacher.getId();
			return getUserStudents(teacherID);
		}
		return null;
	}
	
	
	
	public boolean isPermittedPage(String sessionID, URI uri) {
		User user = loggedInUsers.get(sessionID);
		String uriStr = uri.normalize().toASCIIString();
		
		if (user == null) {
			return !uriStr.matches("/s/.*");
		}
		switch (user.getRole()) {
			case ADMIN:
				return true;
			case STUDENT:
				return isStudentPage(uriStr);
			case TEACHER:
				return isTeacherPage(uriStr);
		}
		
		return true;
	}

	public boolean isStudentPage(String uri) {
		if (uri.matches("/s/teacher.html.*")) {
			return false;
		}
		if (uri.matches("/s/fragments.html.*")) {
			return false;
		}
		return true;
	}

	public boolean isTeacherPage(String uri) {
		return true;
	}
	
}


