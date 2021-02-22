package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizStudent {

	public WebDriver driver;
	
	@FindBy(id="menubar")
	public WebElement menubar;
	
	@FindBy(id="btn2")	//btn2 represent test id number and probably need to change and make sure it is correct
	public WebElement btn2;

	@FindBy(id = "log_out")
	public WebElement log_out;
	
	public QuizStudent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
