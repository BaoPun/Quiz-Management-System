package com.project2.demo.DAO;

import java.util.List;

import com.project2.demo.beans.Answer;
import com.project2.demo.beans.Question;

public interface DBInterface {

	//Questions
	public Question addQuestion(Question a);
	public Question getQuestion(int id);
	public Question getQuestion(String name);
	public List<Question> getAllQuestions();
	public Question updateQuestion(Question change);
	public boolean deleteQuestion(int id);
	
	//Answers
	public Answer addAnswer(Answer a);
	public Answer getAnswer(int id);
	public Answer getAnswer(String name);
	public List<Answer> getAllAnswers();
	public Answer updateAnswer(Answer change);
	public boolean deleteAnswer(int id);
	public List<Answer> getAnswersFromQuestion(int questionid);

	
}
