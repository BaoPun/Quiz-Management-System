Feature: Quiz login link works

Scenario: Quiz Login works for Test
	Given Teacher is on the Quiz Home Page
	When The teacher fill username field with "charlie" for login
	And The teacher fill password with "bar" for login
	And The teacher press the "Let me in." button for login
	Then The teacher should be on the Teacher Page after login
	
	
      
