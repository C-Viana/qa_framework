package steps.frontend;

import java.util.List;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SortablePage;

public class SortableDefinitions {

    private final SortablePage page;
    private static List<String> expectedOrder = null;

    public SortableDefinitions(SortablePage sortablePage) {
        this.page = sortablePage;
    }

    @When("I arrange all elements in the following order")
    public void i_arrange_all_elements_in_the_following_order(io.cucumber.datatable.DataTable dataTable) {
        expectedOrder = dataTable.values();
        page.arrange(expectedOrder);
    }

    @Then("all positions will be changed accordingly")
    public void all_positions_will_be_changed_accordingly() {
        page.validateArrangement(expectedOrder);
    }
}
