package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizPage {

	public WebDriver driver;

	@FindBy(id="question")
	public WebElement question;
	
	@FindBy(id="answer1")
	public WebElement answer1;
	
	@FindBy(id="next")
	public WebElement next;
	
	@FindBy(id="submit")
	public WebElement submit;
	
	public QuizPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
}
