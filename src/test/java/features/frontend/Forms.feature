@TestForms
Feature: Book CRUD	
	@TestForms01
    Scenario: Fill form page with valid data
        Given I navigate to "Forms"
        And open the 'Practice Form'
        When I fill all fields with valid data
        Then my data is confirmed on a modal window