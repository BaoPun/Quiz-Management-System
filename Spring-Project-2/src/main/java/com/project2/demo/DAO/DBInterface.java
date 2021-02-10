package com.project2.demo.DAO;

import java.util.List;

import com.project2.demo.beans.Question;

public interface DBInterface {

	public Question addQuestion(Question a);
	public Question getQuestion(int id);
	public Question getQuestion(String name);
	public List<Question> getAllQuestions();
	public Question updateQuestion(Question change);
	public boolean deleteQuestion(int id);

	
}
