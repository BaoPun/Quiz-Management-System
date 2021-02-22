Feature: Assigning Quiz

Background: Teacher logs into the account
	Given The teacher on the login page on URL "http://localhost:8080/" for assign quiz
	When The teacher fill in username with "Test" for assign quiz
	And The teacher fill in password with "bar" for assign quiz
	And The teacher click on the "Let me in." button for assign quiz
	Then The teacher on the "Teacher Page" page for assign quiz
	
Scenario: The assigning a quiz to a student
	When The teacher click on the "Assign Quiz" button for get in assign quiz page
	Then The teacher move to assigning page for assign quiz 
	And The teacher able to see student list for assign quiz
	And The teacher able to see quiz list for assign quiz
	And The teacher fill student_id with "9" for assign quiz
	And The teacher fill quiz_id with "69" for assign quiz
	And The teacher click "Assign It" assign quiz button for assign quiz to student 
	Then The page alert the teacher with "Succesfully assigned" for assign quiz
	And The teacher click "Okay" okay button for assign quiz
	And The teacher move to "Teacher Page" after assigned quiz to student
	And The teacher click "Log Out" log out after assigned quiz to student
	

			
			
			
			
			
			
			
			
			
			
			
			