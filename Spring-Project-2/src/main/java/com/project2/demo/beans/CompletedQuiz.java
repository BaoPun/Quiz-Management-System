package com.project2.demo.beans;

import java.util.List;
import java.util.Set;

public class CompletedQuiz {
	
	private List<Progress> answers;
	
	private double score;
	
	private Set<QuestionAnswerPair> questions;

	public CompletedQuiz() {
		super();
	}

	public CompletedQuiz(List<Progress> answers, double score, Set<QuestionAnswerPair> questions) {
		super();
		this.answers = answers;
		this.score = score;
		this.questions = questions;
	}

	public List<Progress> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Progress> answers) {
		this.answers = answers;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Set<QuestionAnswerPair> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionAnswerPair> questions) {
		this.questions = questions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		CompletedQuiz other = (CompletedQuiz) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompletedQuiz [answers=" + answers + ", score=" + score + ", questions=" + questions + "]";
	}

}
