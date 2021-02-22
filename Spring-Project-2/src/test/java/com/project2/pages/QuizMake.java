package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizMake {

	public WebDriver driver;
	
	
	@FindBy(id = "quiz-name-input")
	public WebElement quizNameInput;
	
	@FindBy(id = "question-box-0")
	public WebElement question1;
	
	@FindBy(id = "text-box-0-0")
	public WebElement answer1;

	@FindBy(id = "text-box-0-1")
	public WebElement answer2;

	@FindBy(id = "text-box-0-2")
	public WebElement answer3;

	@FindBy(id = "text-box-0-3")
	public WebElement answer4;
 
	@FindBy(id="checkbox-0-0")
	public WebElement correct1; 
	
	@FindBy(id = "question-box-1")
	public WebElement question2;
	
	@FindBy(id = "text-box-1-0")
	public WebElement answer21;

	@FindBy(id = "text-box-2-1")
	public WebElement answer22;

	@FindBy(id = "text-box-1-2")
	public WebElement answer23;

	@FindBy(id = "text-box-1-3")
	public WebElement answer24;
 
	@FindBy(id="checkbox-1-0")
	public WebElement correct2; 
	
	@FindBy(xpath = "/html/body/div[2]/button[1]")
	public WebElement add_button;
	
	@FindBy(xpath = "/html/body/div[2]/button[2]")
	public WebElement submit_quiz;

	public QuizMake(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
