Feature: Take quiz

Background: Student logs in to profile
	Given The student on the login page on URL "http://localhost:8080/" for take quiz
	When The student fill in username with "Bob" for take quiz
	And The student fill in password with "bar" for take quiz
	And The student click on the "Let me in." button for take quiz
	Then The student on the "Student Page" page for take quiz
	
Scenario: The student open the quiz
	When The student click on the "Menu" sign for take quiz
	And The student choose the "Test Quiz"  for take quiz
	Then The student on the Test Quiz page for take quiz
	And The student the click the "answer1" for first question
	And The student click "Next" button for pass the next question
	Then The student move to next question to see second question
	And The student the click the "answer1" for questiontwo
	And The student click "Submit" button for complete quiz
	Then The student move to Score page after complete quiz 
	And The student click to "Log Out" after complete quiz to log out
	Then The student move to Login Page  after log out 