package com.project2.steps;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.project2.pages.QuizLogin;
import com.project2.pages.QuizRegister;
import com.project2.runners.QuizRegisterRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuizRegisterStepImpl {

	public static QuizLogin quizlogin= QuizRegisterRunner.quizlogin;
	public static WebDriver driver = QuizRegisterRunner.driver;
	public static QuizRegister quizregister=new QuizRegister(driver);
	
	@Given("^The student on the login page on URL \"([^\"]*)\" for register$")
	public void the_student_on_the_login_page_on_URL_for_register(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@When("^The student click \"([^\"]*)\" button for register$")
	public void the_student_click_button_for_register(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}

	@Then("^The student move to \"([^\"]*)\" for register$")
	public void the_student_move_to_for_register(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@When("^The student fill username with \"([^\"]*)\" for register$")
	public void the_student_fill_username_with_for_register(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@When("^The student fill the password with \"([^\"]*)\" for register$")
	public void the_student_fill_the_password_with_for_register(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  
	}

	@When("^The student select option \"([^\"]*)\" from teacherList for register$")
	public void the_student_select_option_from_teacherList_for_register(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}

	@When("^The student click \"([^\"]*)\" button for submit new account$")
	public void the_student_click_button_for_submit_new_account(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("^The student get alert with \"([^\"]*)\" after submit$")
	public void the_student_get_alert_with_after_submit(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}

}
