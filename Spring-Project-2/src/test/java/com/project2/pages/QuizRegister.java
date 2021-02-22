package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizRegister {

	public WebDriver driver;
	
	@FindBy(id = "username")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id = "teacherList")
	public WebElement teacherList;
	
	@FindBy(id = "registerUser")
	public WebElement registerUser;

	public QuizRegister(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
