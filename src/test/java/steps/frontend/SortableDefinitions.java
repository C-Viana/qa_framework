package steps.frontend;

import java.util.List;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SortablePage;

public class SortableDefinitions extends SortablePage {

    private static List<String> expectedOrder = null;

    @When("I arrange all elements in the following order")
    public void i_arrange_all_elements_in_the_following_order(io.cucumber.datatable.DataTable dataTable) {
        expectedOrder = dataTable.values();
        arrange(expectedOrder);
    }

    @Then("all positions will be chenged accordingly")
    public void all_positions_will_be_chenged_accordingly() {
        validateArrangement(expectedOrder);
    }
}
