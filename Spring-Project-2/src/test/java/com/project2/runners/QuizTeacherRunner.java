package com.project2.runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.project2.pages.QuizLogin;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.project2.steps")
public class QuizTeacherRunner {

	public static WebDriver driver;
	public static QuizLogin quizLogin;

	@BeforeClass
	public static void setUp() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

		driver = new ChromeDriver();
		quizLogin = new QuizLogin(driver);

	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
