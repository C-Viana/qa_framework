@TestBackendBook
Feature: Book CRUD	
	@TestBackendBook01
    Scenario: Book fetch at /BookStore/v1/Books
        Given I request all books showcase
        Then my request will be status code 200
        And a list of all books will be returned
    
    @TestBackendBook02
    Scenario: Book reservation /BookStore/v1/Books
        Given I have a valid user ID
        When I request a reservation for the book "9781449331818"
        Then my request will be status code 201
        And the required books are listed in the response
    
    @TestBackendBook03
    Scenario: Remove reservation /BookStore/v1/Books
        Given I have a valid user ID
        When I remove reservation for user ID
        Then my request will be status code 204