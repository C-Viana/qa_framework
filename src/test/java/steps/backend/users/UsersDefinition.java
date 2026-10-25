package steps.backend.users;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;
import reporter.Reports;
import requests.UserRequests;
import steps.backend.ScenarioContext;

public class UsersDefinition {

    private final UserRequests userRequests;
    private final ScenarioContext ctx;
    private final Faker faker;

    public UsersDefinition(UserRequests userRequests, ScenarioContext ctx) {
        this.userRequests = userRequests;
        this.ctx = ctx;
        this.faker = new Faker();
    }

    @Given("I have a username and password")
    public void i_have_an_username_and_password() {
        ctx.username = faker.name().fullName();
        ctx.password = faker.credentials().password(8, 12, true, true, true);
        ctx.password += "#2P";

        userRequests.validateEquals( false, (ctx.username.isBlank() && ctx.password.isBlank()), "User data must be set.\nUsername: "+ ctx.username + "\nPassword: " + ctx.password );
    }

    @When("posting my data to create a new user")
    public void posting_my_data_to_create_a_new_user() {
        ctx.response = userRequests.create(ctx.username, ctx.password);
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
        ctx.responseBody = userRequests.toJson(ctx.response.asString());
    }

    @Then("a user ID will be created")
    public void an_user_id_will_be_created() {
        ctx.userId = ctx.responseBody.get("userID").asString();
        userRequests.validateEquals(false, ctx.userId.isBlank(), "User ID created: " + ctx.userId);
    }
    
    @Given("I have a valid credential")
    public void i_have_a_valid_credential() {
        userRequests.validateEquals( false, (ctx.username.isBlank() && ctx.password.isBlank()), "User data must be set.\nUsername: "+ ctx.username + "\nPassword: " + ctx.password );
    }

    @When("posting my data to authenticate access")
    public void posting_my_data_to_authenticate_access() {
        ctx.response = userRequests.authenticate(ctx.username, ctx.password);
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
        ctx.responseBody = userRequests.toJson(ctx.response.asString());
    }

    @And("an access token will be created")
    public void an_access_token_will_be_created() {
        ctx.token = ctx.responseBody.get("token").asString();
        userRequests.validateEquals(false, ctx.token.isBlank(), "User ID created: " + ctx.token);
    }

    @When("posting my data to check authorization access")
    public void posting_my_data_to_check_authorization_access() {
        ctx.response = userRequests.authorization(ctx.username, ctx.password);
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
        ctx.responseBody = userRequests.toJson(ctx.response.asString());
    }

    @And("my authorization will be true")
    public void my_authorization_will_be_true() {
        Boolean result = ctx.response.body().as(Boolean.class);
        userRequests.validateEquals(true, result, "User authorization is valid: " + result);
    }

    @When("I request my reservation showcase")
    public void i_request_my_reservation_showcase() {
        ctx.response = userRequests.reservation(ctx.userId, ctx.username, ctx.password);
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
        ctx.responseBody = userRequests.toJson(ctx.response.asString());
    }
    
}
