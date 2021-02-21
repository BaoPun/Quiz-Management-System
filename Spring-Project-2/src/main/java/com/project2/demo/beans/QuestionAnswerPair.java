package com.project2.demo.beans;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerPair {

	private Question question;
	
	private List<Answer> answers;

	public QuestionAnswerPair(Question question, List<Answer> answers) {
		super();
		this.question = question;
		this.answers = answers;
	}
	
	public QuestionAnswerPair() {
		super();
		this.answers = new ArrayList<Answer>();
	}

	@Override
	public String toString() {
		return "QuestionAnswerPair [question=" + question + ", answers=" + answers + "]";
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionAnswerPair other = (QuestionAnswerPair) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}
	
	
	
}
