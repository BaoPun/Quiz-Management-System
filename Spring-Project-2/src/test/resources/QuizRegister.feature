Feature: Register

Background: The student enter the log in page
	Given The student on the login page on URL "http://localhost:8080/" for register
	When The student click "Register" button for register
	Then The student move to "Register Page" for register

Scenario: The student register register to quiz system
	When The student fill username with "TestUser" for register
	And The student fill the password with "bar" for register
	And The student select option "TestTeacher" from teacherList for register 
	And The student click "Submit New Account" button for submit new account
	Then The student get alert with "Succesfully Registered" after submit
	