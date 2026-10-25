@TestBackendAccount
Feature: User CRUD	
	@TestBackendAccount01
    Scenario: User creation at /Account/v1/User 
        Given I have a username and password
        When posting my data to create a new user
        Then my request will be status code 201
        And a user ID will be created
    
    @TestBackendAccount02
    Scenario: User authentication at /Account/v1/GenerateToken
        Given I have a valid credential
        When posting my data to authenticate access
        Then my request will be status code 200
        And an access token will be created
    
    @TestBackendAccount03
    Scenario: User authorization at /Account/v1/Authorized
        Given I have a valid credential
        When posting my data to check authorization access
        Then my request will be status code 200
        And my authorization will be true
    
    @TestBackendAccount04
    Scenario: Book reservation /Account/v1/User/userID
        Given I have a valid user ID
        When I request my reservation showcase
        Then my request will be status code 200
        And the required books are listed in the response