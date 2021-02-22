package com.project2.demo.beans;

public class UserSubmittedProgress {

	private int questionNum;
	private int answerNum;
	
	public UserSubmittedProgress() {
	}
	
	

	public UserSubmittedProgress(int questionNum, int answerNum) {
		super();
		this.questionNum = questionNum;
		this.answerNum = answerNum;
	}



	public int getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	@Override
	public String toString() {
		return "UserSubmittedProgress [questionNum=" + questionNum + ", answerNum=" + answerNum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerNum;
		result = prime * result + questionNum;
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
		UserSubmittedProgress other = (UserSubmittedProgress) obj;
		if (answerNum != other.answerNum)
			return false;
		if (questionNum != other.questionNum)
			return false;
		return true;
	}
	
	

}
