package steps.backend.users;

import com.github.javafaker.Faker;

import engine.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import requests.UserRequests;

public class UsersDefinition extends UserRequests {

    private String username, password, userId, token;

    @Before
    void startCase() {
        reporter.startTest("User Scenario");
    }

    @After
    void finishCase() {
        response = null;
        responseBody = null;
    }

    @Given("I have an username and password")
    public void i_have_an_username_and_password() {
        username = Faker.instance().name().fullName();
        password = Faker.instance().internet().password(8, 12, true, true, true);
        password += "#2P";
        validateEquals( false, (username.isBlank() && password.isBlank()), "User data must be set.\nUsername: "+ username + "\nPassword: " + password );
    }

    @When("posting my data to create a new user")
    public void posting_my_data_to_create_a_new_user() {
        response = create(username, password);
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
        responseBody = toJson(response.asString());
    }

    @Then("an user ID will be created")
    public void an_user_id_will_be_created() {
        userId = responseBody.get("userID").asString();
        validateEquals(false, userId.isBlank(), "User ID created: " + userId);
    }
    
    @Given("I have a valid credential")
    public void i_have_a_valid_credential() {
        //TODO: parametrizar credenciais
        username = "Miss Dimple Lowe";
        password = "s#NX6v76!J6VF";
        validateEquals( false, (username.isBlank() && password.isBlank()), "User data must be set.\nUsername: "+ username + "\nPassword: " + password );
    }

    @When("posting my data to authenticate access")
    public void posting_my_data_to_authenticate_access() {
        response = authenticate(username, password);
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
        responseBody = toJson(response.asString());
    }

    @And("an access token will be created")
    public void an_access_token_will_be_created() {
        token = responseBody.get("token").asString();
        validateEquals(false, token.isBlank(), "User ID created: " + token);
    }

    @When("posting my data to check authorization access")
    public void posting_my_data_to_check_authorization_access() {
        response = authorization(username, password);
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
        responseBody = toJson(response.asString());
    }

    @And("my authorization will be true")
    public void my_authorization_will_be_true() {
        Boolean result = response.body().as(Boolean.class);
        validateEquals(true, result, "User authorization is valid: " + result);
    }

    @When("I request my reservation showcase")
    public void i_request_my_reservation_showcase() {
        response = reservation(TestBase.get("userId").toString());
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
        responseBody = toJson(response.asString());
    }
    
}
