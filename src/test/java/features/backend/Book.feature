@Test2
Feature: Book CRUD	
	@Test2A
    Scenario: Book fetch at /BookStore/v1/Books
        Given I request all books showcase
        Then my request will be status code 200
        And a list of all books will be returned
    
    @Test2B
    Scenario: Book reservation /BookStore/v1/Books
        Given I have a valid user ID
        When I request a reservation for the book "9781449331818"
        Then my request will be status code 201
        And the required books are listed in the response
    
    @Test3B
    Scenario: Remove reservation /BookStore/v1/Books
        Given I have a valid user ID
        When I remove reservation for user ID "89519514-630f-4a89-ad86-80340b9b2da1"
        Then my request will be status code 204