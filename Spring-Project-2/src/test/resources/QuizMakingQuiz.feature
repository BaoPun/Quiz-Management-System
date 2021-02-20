Feature: Make Quiz

Background: Teacher logs into the account
	Given The teacher on the login page on URL "http://localhost:8080/" for make quiz
	When The teacher fill in username with "Test"	for make quiz
	And The teacher fill in password with "bar" for make quiz
	And The teacher click on the "Let me in." button for make quiz
	Then The teacher on the "Teacher Page" page for make quiz
	
Scenario: The teacher make a quiz
	When The teacher click on the "Make Quiz" button 
	Then The teacher move to Make Quiz page
	And The teacher fill in quiz Name with "Cucumber Quiz"
	And The teacher fill in questionone with "question1"
	And The teacher fill in answerone with "answer1-1"
	And The teacher fill in answertwo with "answer1-2"
	And The teacher fill in answerthree with "answer1-3"
	And The teacher fill in answerfour with "answer1-4"
	And The teacher chooses correct answer.
	And The teacher click "Add more question" button for add more question
	Then The teacher will be able to add second question.
	And The teacher fill in questiontwo with "question2"
	And The teacher fill in answerone with "answer2-1" for questiontwo
	And The teacher fill in answertwo with "answer2-2" for questiontwo
	And The teacher fill in answerthree with "answer2-3" for questiontwo
	And The teacher fill in answerfour with "answer2-4" for questiontwo
	And The teacher chooses correct answer for questiontwo.	
	And The teacher will click "Complete Quiz" button for complete quiz.
	Then The teacher will move to Teacher page after complete making quiz.
	
	

			
			
			
			
			