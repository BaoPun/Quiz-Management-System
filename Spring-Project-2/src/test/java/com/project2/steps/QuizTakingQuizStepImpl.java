package com.project2.steps;

import org.openqa.selenium.WebDriver;

import com.project2.pages.QuizLogin;
import com.project2.pages.QuizPage;
import com.project2.pages.QuizScore;
import com.project2.pages.QuizStudent;
import com.project2.runners.QuizStudentRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuizTakingQuizStepImpl {
	public static QuizLogin quizLogin = QuizStudentRunner.quizlogin;
	public static WebDriver driver = QuizStudentRunner.driver;
	public static QuizStudent quizstudent=new QuizStudent(driver);
	public static QuizPage quizpage=new QuizPage(driver);
	public static QuizScore quizscore=new QuizScore(driver);

	@Given("^The student on the login page on URL \"([^\"]*)\" for take quiz$")
	public void the_student_on_the_login_page_on_URL_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The student fill in username with \"([^\"]*)\" for take quiz$")
	public void the_student_fill_in_username_with_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The student fill in password with \"([^\"]*)\" for take quiz$")
	public void the_student_fill_in_password_with_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The student click on the \"([^\"]*)\" button for take quiz$")
	public void the_student_click_on_the_button_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student on the \"([^\"]*)\" page for take quiz$")
	public void the_student_on_the_page_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^The student click on the \"([^\"]*)\" sign for take quiz$")
	public void the_student_click_on_the_sign_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The student choose the \"([^\"]*)\"  for take quiz$")
	public void the_student_choose_the_for_take_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student on the Test Quiz page for take quiz$")
	public void the_student_on_the_Test_Quiz_page_for_take_quiz() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student the click the \"([^\"]*)\" for first question$")
	public void the_student_the_click_the_for_first_question(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student click \"([^\"]*)\" button for pass the next question$")
	public void the_student_click_button_for_pass_the_next_question(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student move to next question to see second question$")
	public void the_student_move_to_next_question_to_see_second_question() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student the click the \"([^\"]*)\" for questiontwo$")
	public void the_student_the_click_the_for_questiontwo(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student click \"([^\"]*)\" button for complete quiz$")
	public void the_student_click_button_for_complete_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student move to Score page after complete quiz$")
	public void the_student_move_to_Score_page_after_complete_quiz() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student click to \"([^\"]*)\" after complete quiz to log out$")
	public void the_student_click_to_after_complete_quiz_to_log_out(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The student move to Login Page  after log out$")
	public void the_student_move_to_Login_Page_after_log_out() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	
	

	
	
	
	
}
