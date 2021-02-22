package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizAssign {

	public WebDriver driver;
	
	@FindBy(id = "student_id")
	public WebElement student_id;
	
	@FindBy(id = "quiz_id")
	public WebElement quiz_id;
	
	@FindBy(id = "assign_button")
	public WebElement assign_button;
	
	public QuizAssign(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	
}
