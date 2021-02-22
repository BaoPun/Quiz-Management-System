package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizMake {

	public WebDriver driver;
	
	@FindBy(id = "quiz_name")
	public WebElement title;
	
	@FindBy(id = "question")
	public WebElement question;
	
	@FindBy(id = "answer1")
	public WebElement answer1;

	@FindBy(id = "answer2")
	public WebElement answer2;

	@FindBy(id = "answer3")
	public WebElement answer3;

	@FindBy(id = "answer4")
	public WebElement answer4;
	
	@FindBy(id = "add_button")
	public WebElement add_button;
	
	@FindBy(id = "complete_button")
	public WebElement complete_button;

	public QuizMake(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
