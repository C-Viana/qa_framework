package steps.frontend;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.WebTablesPage;

public class WebTablesDefinitions extends WebTablesPage {

    private static List<Map<String,String>> registries = null;

    @When("I create a new registry")
    public void i_create_a_new_registry() {
        openNewRegistry();
    }

    @Then("I create {int} new registries")
    public void a_new_line_will_be_persisted(int quantity) {
        fillRandomData(quantity);
    }

    @And ("the new lines will be persisted")
    public void a_new_line_will_be_persisted() {
        validateCreatedRegistries();
    }

    @When("I edit registries with the following data")
    public void i_edit_registries_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        registries = dataTable.asMaps();
        registries.stream().forEach( reg -> {
            searchRegistry(reg.get("Search"));
            editRegistry(reg);
        });
    }

    @Then("the changes will be persisted")
    public void the_changes_will_be_persisted() {
        registries.stream().forEach( reg -> {
            searchRegistry(reg.get("Email"));
            validateRegistryFields(reg);
        });
    }

    @When("I delete registries with the following data")
    public void i_delete_registries_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        registries = dataTable.asMaps();
        registries.stream().forEach( reg -> {
            searchRegistry(reg.get("Search"));
            deleteRegistry();
        });
    }

    @Then("the registries will no more be available")
    public void the_registries_will_no_more_be_available() {
        registries.stream().forEach( reg -> {
            validateRegistryIsMissing(reg.get("Search"));
        });
    }

    @Then("I can delete all these registries")
    public void i_can_delete_all_these_registries() {
        deleteCreatedRegistries();
    }

}
