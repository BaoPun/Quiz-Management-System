package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="progress")
public class Progress
{

	@Id
	private int id;
	/*
	 * CREATE TABLE progress(  id NUMBER PRIMARY KEY,
                        userid NUMBER,
                        answerid NUMBER);
	 */
	
	
}
