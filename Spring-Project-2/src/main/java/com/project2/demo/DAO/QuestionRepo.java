package com.project2.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project2.beans.Questions;


public interface QuestionRepo {

	public int addQuestion(Questions a);
	public Questions getQuestion(int id);
	public Questions getQuestion(String name);
	public List<Questions> getAllQuestions();
	public Questions updateQuestion(Questions change);
	public boolean deleteQuestion(int id);


}
