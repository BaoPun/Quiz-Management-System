package com.project2.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {


	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="username")
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ ", teacher=" + teacher + "]";
	}

	@Column(name="passwordhash")
	private String passwordHash;
	
	@Enumerated(EnumType.STRING)
	@Column(length=10,name="role")
	private UserType role;
	
	private int teacher;
	
	public User() {
		
		
	}

}
