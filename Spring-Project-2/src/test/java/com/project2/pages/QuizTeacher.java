package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizTeacher {
	
	public WebDriver driver;
	
	@FindBy(id="score")
	public WebElement score;
	
	@FindBy(id="make_quiz")
	public WebElement make_quiz;
	
	@FindBy(id="score_button")
	public WebElement score_button;
	
	@FindBy(id = "log_out")
	public WebElement log_out;
	
	@FindBy(id = "time")
	public WebElement time;
	
	public QuizTeacher(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


}
