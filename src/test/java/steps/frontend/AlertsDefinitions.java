package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BrowserWindowsPage;

public class AlertsDefinitions extends BrowserWindowsPage {

    @When("I click to open a new window")
    public void i_click_to_open_a_new_window() {
        openNewWindow();
    }

    @And ("change to the new window")
    public void change_to_the_new_window() {
        changeWindow(1);
    }

    @Then("a sample message will be displayed as {string}")
    public void a_sample_message_will_be_displayed(String message) {
        validateSampleMessage(message);
    }

}
