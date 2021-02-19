package com.project2.demo.beans;

import java.util.List;

public class NewQuiz {

	private List<List<String>> answers;
	private List<String> questions;
	private String name;
	private List<List<Boolean>> answerCorrectness;
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerCorrectness == null) ? 0 : answerCorrectness.hashCode());
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
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
		NewQuiz other = (NewQuiz) obj;
		if (answerCorrectness == null) {
			if (other.answerCorrectness != null)
				return false;
		} else if (!answerCorrectness.equals(other.answerCorrectness))
			return false;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewQuiz [answers=" + answers + ", questions=" + questions + ", name=" + name + ", answerCorrectness="
				+ answerCorrectness + "]";
	}

	public List<List<Boolean>> getAnswerCorrectness() {
		return answerCorrectness;
	}

	public void setAnswerCorrectness(List<List<Boolean>> answerCorrectness) {
		this.answerCorrectness = answerCorrectness;
	}

	public List<List<String>> getAnswers() {
		return answers;
	}

	public void setAnswers(List<List<String>> answers) {
		this.answers = answers;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NewQuiz() {
		super();
	}
	
	

}
