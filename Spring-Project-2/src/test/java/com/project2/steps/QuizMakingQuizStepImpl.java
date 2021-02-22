package com.project2.steps;

import org.openqa.selenium.WebDriver;

import com.project2.pages.QuizLogin;
import com.project2.runners.MakingQuizRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QuizMakingQuizStepImpl {
	
	public static QuizLogin quizlogin= MakingQuizRunner.quizlogin;
	public static WebDriver driver = MakingQuizRunner.driver;
	
	@Given("^The teacher on the login page on URL \"([^\"]*)\" for make quiz$")
	public void the_teacher_on_the_login_page_on_URL_for_make_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The teacher fill in username with \"([^\"]*)\"	for make quiz$")
	public void the_teacher_fill_in_username_with_for_make_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The teacher fill in password with \"([^\"]*)\" for make quiz$")
	public void the_teacher_fill_in_password_with_for_make_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^The teacher click on the \"([^\"]*)\" button for make quiz$")
	public void the_teacher_click_on_the_button_for_make_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher on the \"([^\"]*)\" page for make quiz$")
	public void the_teacher_on_the_page_for_make_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^The teacher click on the \"([^\"]*)\" button$")
	public void the_teacher_click_on_the_button(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher move to Make Quiz page$")
	public void the_teacher_move_to_Make_Quiz_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in quiz Name with \"([^\"]*)\"$")
	public void the_teacher_fill_in_quiz_Name_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in questionone with \"([^\"]*)\"$")
	public void the_teacher_fill_in_questionone_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answerone with \"([^\"]*)\"$")
	public void the_teacher_fill_in_answerone_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answertwo with \"([^\"]*)\"$")
	public void the_teacher_fill_in_answertwo_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answerthree with \"([^\"]*)\"$")
	public void the_teacher_fill_in_answerthree_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answerfour with \"([^\"]*)\"$")
	public void the_teacher_fill_in_answerfour_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher chooses correct answer\\.$")
	public void the_teacher_chooses_correct_answer() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher click \"([^\"]*)\" button for add more question$")
	public void the_teacher_click_button_for_add_more_question(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher will be able to add second question\\.$")
	public void the_teacher_will_be_able_to_add_second_question() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in questiontwo with \"([^\"]*)\"$")
	public void the_teacher_fill_in_questiontwo_with(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answerone with \"([^\"]*)\" for questiontwo$")
	public void the_teacher_fill_in_answerone_with_for_questiontwo(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answertwo with \"([^\"]*)\" for questiontwo$")
	public void the_teacher_fill_in_answertwo_with_for_questiontwo(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answerthree with \"([^\"]*)\" for questiontwo$")
	public void the_teacher_fill_in_answerthree_with_for_questiontwo(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher fill in answerfour with \"([^\"]*)\" for questiontwo$")
	public void the_teacher_fill_in_answerfour_with_for_questiontwo(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher chooses correct answer for questiontwo\\.$")
	public void the_teacher_chooses_correct_answer_for_questiontwo() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher will click \"([^\"]*)\" button for complete quiz\\.$")
	public void the_teacher_will_click_button_for_complete_quiz(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The teacher will move to Teacher page after complete making quiz\\.$")
	public void the_teacher_will_move_to_Teacher_page_after_complete_making_quiz() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
