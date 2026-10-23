package steps.backend;

import common.TestSupport;
import io.cucumber.java.en.Then;

public class GenericDefinition extends TestSupport {

    @Then("my request will be status code {int}")
    public void my_request_will_be_status_code(Integer expectedStatusCode) {
        validateEquals(expectedStatusCode, response.statusCode(), "Status code is "+expectedStatusCode);
    }
}
