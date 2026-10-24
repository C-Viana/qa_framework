package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProgressBarPage;

public class WidgetsDefinitions extends ProgressBarPage {

    @When("I click to start loading")
    public void i_click_to_start_loading() {
        startProgressBar();
    }

    @And("click to stop loading between {int} and {int} percent")
    public void click_to_stop_loading_between_and_percent(Integer min, Integer max) {
        stopProgressBarAt(min, max);
    }

    @Then("the progress bar will be stopped between {int} and {int} percent")
    public void the_progress_bar_will_be_stopped_between_and_percent(Integer min, Integer max) {
        validateProgressBarAt(min, max);
    }
    @Then("I validate the completion of the progress bar")
    public void i_validate_the_completion_of_the_progress_bar() {
        validateProgressBarCompleted();
    }
    @Then("click to reset the progress bar")
    public void click_to_reset_the_progress_bar() {
        resetProgressBar();
    }

}
