package com.project2.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.project2.pages.QuizLogin;
import com.project2.pages.QuizTeacher;
import com.project2.runners.QuizViewScoreRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuizViewScoresStepImpl {

	public static QuizLogin quizlogin= QuizViewScoreRunner.quizlogin;
	public static WebDriver driver = QuizViewScoreRunner.driver;
	public static QuizTeacher quizteacher= new QuizTeacher(driver);
	
	@Given("^The teacher is on the login page for see score$")
	public void the_teacher_is_on_the_login_page_for_see_score() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://localhost:8080/");
		Thread.sleep(1000);
	}

	@When("^The teacher fill in username with \"([^\"]*)\" for score$")
	public void the_teacher_fill_in_username_with_for_score(String username) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizlogin.username.sendKeys(username);
		Thread.sleep(1000);
	}

	@When("^The teacher fill in password with \"([^\"]*)\" for score$")
	public void the_teacher_fill_in_password_with_for_score(String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizlogin.password.sendKeys(password);
		Thread.sleep(1000);
	}

	@When("^The teacher click on the \"([^\"]*)\" button for score$")
	public void the_teacher_click_on_the_button_for_score(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizlogin.loginButton.click();
		Thread.sleep(1000);
	}

	@Then("^The teacher on the \"([^\"]*)\" page for score$")
	public void the_teacher_on_the_page_for_score(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("Teacher Administration Page",driver.getTitle());
		Thread.sleep(1000);
	}

	@Then("^The teacher click for one of student of button for score$")
	public void the_teacher_click_for_one_of_student_of_button_for_score() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    quizteacher.student.click();
	    Thread.sleep(2000);
	    
	    
	}

	@Then("^The teacher see the grades for one of his students for score$")
	public void the_teacher_see_the_grades_for_one_of_his_students_for_score() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		quizteacher.quiz.click();
		Thread.sleep(2000);
	}


	
	
	

}
