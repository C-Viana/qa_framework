@TestAlerts
Feature: Alerts	
	@TestAlerts01
    Scenario: Open new browser tab
        Given I navigate to "alerts"
        And open the 'Browser Windows'
        When I click to open a new window
        And change to the new window
        Then a sample message will be displayed as "This is a sample page"