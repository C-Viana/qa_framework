package steps.backend;

import common.TestSupport;
import io.cucumber.java.en.Then;

public class GenericDefinition {
    private final TestSupport testSupport;
    private final ScenarioContext ctx;

    public GenericDefinition(TestSupport testSupport, ScenarioContext ctx) {
        this.testSupport = testSupport;
        this.ctx = ctx;
    }

    @Then("my request will be status code {int}")
    public void my_request_will_be_status_code(Integer expectedStatusCode) {
        testSupport.validateEquals(expectedStatusCode, ctx.response.statusCode(), "Status code is "+expectedStatusCode);
    }
}
