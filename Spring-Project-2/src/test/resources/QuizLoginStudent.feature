Feature: Quiz login link works

Scenario: Quiz Login works for Bob
	Given The student is on the Quiz Home Page for login
	When The student fill username field with "Bob" for login
	And The student fill password with "bar" for login
	And The student press the "Let me in." button for login
	Then The student should be on the Student Page for login
	

	
	