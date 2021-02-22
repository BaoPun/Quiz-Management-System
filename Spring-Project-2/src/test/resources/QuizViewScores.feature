Feature: View Quiz Scores

Background: 
	Given The teacher is on the login page for see score
Scenario: Teacher see the scores
	When The teacher fill in username with "Test" for score
	And The teacher fill in password with "bar" for score
	And The teacher click on the "Let me in." button for score
	Then The teacher on the "Teacher Page" page for score
	And The teacher click for one of student of button for score
	Then The teacher see the grades for one of his students for score
