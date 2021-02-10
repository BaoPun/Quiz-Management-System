package com.project2.demo.DAO;

import java.util.List;

import com.project2.demo.beans.Questions;


public interface QuestionRepo {

	public Questions addQuestion(Questions a);
	public Questions getQuestion(int id);
	public Questions getQuestion(String name);
	public List<Questions> getAllQuestions();
	public Questions updateQuestion(Questions change);
	public boolean deleteQuestion(int id);


}
