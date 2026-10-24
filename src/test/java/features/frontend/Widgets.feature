@TestWidgets
Feature: Widgets	
	@TestWidgets01
    Scenario: Interact with progress bar
        Given I navigate to "widgets"
        And open the 'Progress Bar'
        When I click to start loading
        And click to stop loading between 20 and 25 percent
        Then the progress bar will be stopped between 20 and 25 percent
        When I click to start loading
        Then I validate the completion of the progress bar
        And click to reset the progress bar