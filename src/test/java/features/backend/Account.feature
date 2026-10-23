@Test1
Feature: User CRUD	
	@Test1A
    Scenario: User creation at /Account/v1/User 
        Given I have an username and password
        When posting my data to create a new user
        Then my request will be status code 201
        And an user ID will be created
    
    @Test1B
    Scenario: User authentication at /Account/v1/GenerateToken
        Given I have a valid credential
        When posting my data to authenticate access
        Then my request will be status code 200
        And an access token will be created
    
    @Test1C
    Scenario: User authorization at /Account/v1/Authorized
        Given I have a valid credential
        When posting my data to check authorization access
        Then my request will be status code 200
        And my authorization will be true
    
    @Test1D
    Scenario: Book reservation /Account/v1/User/userID
        Given I have a valid user ID
        When I request my reservation showcase
        Then my request will be status code 200
        And the required books are listed in the response