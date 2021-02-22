package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizLogin {

	public WebDriver driver;
	
	@FindBy(id="username")
	public WebElement username;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(id="loginButton")
	public WebElement loginButton;
	
	@FindBy(id="registerButton")
	public WebElement registerButton;
	
	public QuizLogin(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
