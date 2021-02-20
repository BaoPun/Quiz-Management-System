package com.project2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuizScore {
	
	public WebDriver driver;

	@FindBy(id = "log_out")
	public WebElement log_out;

	public QuizScore(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
