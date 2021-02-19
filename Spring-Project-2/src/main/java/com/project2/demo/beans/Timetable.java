package com.project2.demo.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timetable")
public class Timetable {

	@Id
	@GeneratedValue
	@Column(name="timetableid",updatable=false)
	private int timeTableID;
	
	@Column(name="quizstarttime")
	private Timestamp quizStartTime;
	
	@Column(name="quizendtime")
	private Timestamp quizEndTime;
	
	@Column(name="quizid")
	private int quizID;
	
	@Column(name="userid")
	private int userID;
	
	public Timetable() {
		super();
	}

	public int getTimeTableID() {
		return timeTableID;
	}

	public void setTimeTableID(int timeTableID) {
		this.timeTableID = timeTableID;
	}

	public Timestamp getQuizStartTime() {
		return quizStartTime;
	}

	public void setQuizStartTime(Timestamp quizStartTime) {
		this.quizStartTime = quizStartTime;
	}

	public Timestamp getQuizEndTime() {
		return quizEndTime;
	}

	public void setQuizEndTime(Timestamp quizEndTIme) {
		this.quizEndTime = quizEndTIme;
	}

	public int getQuizID() {
		return quizID;
	}

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Timetable(int timeTableID, Timestamp quizStartTime, Timestamp quizEndTIme, int quizID, int userID) {
		super();
		this.timeTableID = timeTableID;
		this.quizStartTime = quizStartTime;
		this.quizEndTime = quizEndTIme;
		this.quizID = quizID;
		this.userID = userID;
	}

	public Timetable(Timestamp quizStartTime, Timestamp quizEndTIme, int quizID, int userID) {
		super();
		this.quizStartTime = quizStartTime;
		this.quizEndTime = quizEndTIme;
		this.quizID = quizID;
		this.userID = userID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quizEndTime == null) ? 0 : quizEndTime.hashCode());
		result = prime * result + quizID;
		result = prime * result + ((quizStartTime == null) ? 0 : quizStartTime.hashCode());
		result = prime * result + timeTableID;
		result = prime * result + userID;
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
		Timetable other = (Timetable) obj;
		if (quizEndTime == null) {
			if (other.quizEndTime != null)
				return false;
		} else if (!quizEndTime.equals(other.quizEndTime))
			return false;
		if (quizID != other.quizID)
			return false;
		if (quizStartTime == null) {
			if (other.quizStartTime != null)
				return false;
		} else if (!quizStartTime.equals(other.quizStartTime))
			return false;
		if (timeTableID != other.timeTableID)
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timetable [timeTableID=" + timeTableID + ", quizStartTime=" + quizStartTime + ", quizEndTIme="
				+ quizEndTime + ", quizID=" + quizID + ", userID=" + userID + "]";
	}
	
	

}
