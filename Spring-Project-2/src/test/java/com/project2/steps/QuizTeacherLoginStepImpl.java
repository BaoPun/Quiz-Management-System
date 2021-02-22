package com.project2.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.project2.pages.QuizLogin;
import com.project2.runners.QuizTeacherRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuizTeacherLoginStepImpl {

	public static QuizLogin quizlogin=QuizTeacherRunner.quizLogin;
	public static WebDriver driver=QuizTeacherRunner.driver;
	
	@Given("^Teacher is on the Quiz Home Page$")
	public void teacher_is_on_the_Quiz_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://localhost:8080/");
		Thread.sleep(1000);
	}

	@When("^The teacher fill username field with \"([^\"]*)\" for login$")
	public void the_teacher_fill_username_field_with_for_login(String username) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizlogin.username.sendKeys(username);
		Thread.sleep(1000);
	}

	@When("^The teacher fill password with \"([^\"]*)\" for login$")
	public void the_teacher_fill_password_with_for_login(String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizlogin.password.sendKeys(password);
		Thread.sleep(1000);
	}

	@When("^The teacher press the \"([^\"]*)\" button for login$")
	public void the_teacher_press_the_button_for_login(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizlogin.loginButton.click();
		Thread.sleep(1000);
	}

	@Then("^The teacher should be on the Teacher Page after login$")
	public void the_teacher_should_be_on_the_Teacher_Page_after_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("Teacher Administration Page",driver.getTitle());
		Thread.sleep(1000);
	}

	
}
