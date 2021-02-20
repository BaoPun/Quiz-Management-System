package com.project2.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.project2.pages.QuizLogin;
import com.project2.runners.QuizStudentRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuizStudentLoginStepImpl {
	public static QuizLogin quizLogin= QuizStudentRunner.quizlogin;
	public static WebDriver driver = QuizStudentRunner.driver;

	@Given("^The student is on the Quiz Home Page for login$")
	public void the_student_is_on_the_Quiz_Home_Page_for_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://localhost:8080/");
	}

	@When("^The student fill username field with \"([^\"]*)\" for login$")
	public void the_student_fill_username_field_with_for_login(String username) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizLogin.username.sendKeys(username);
	}

	@When("^The student fill password with \"([^\"]*)\" for login$")
	public void the_student_fill_password_with_for_login(String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizLogin.password.sendKeys(password);
	}

	@When("^The student press the \"([^\"]*)\" button for login$")
	public void the_student_press_the_button_for_login(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizLogin.loginButton.click();
	}

	@Then("^The student should be on the Student Page for login$")
	public void the_student_should_be_on_the_Student_Page_for_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("Welcome to the Student Page",driver.getTitle());
	}
	
	
}
