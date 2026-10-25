package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import objects.HomeObjects.categories;
import pages.HomePage;

public class HomeDefinitions {

    private final HomePage page;

    public HomeDefinitions(HomePage homePage) {
        this.page = homePage;
    }

    @Given("I navigate to {string}")
    public void i_navigate_to(String category) {
        page.getCategory( categories.valueOf(category.toUpperCase()) ).click();
    }

    @And("open the 'Practice Form'")
    public void open_the_practice_form() {
        page.openPracticeForm();
    }

    @And("open the 'Browser Windows'")
    public void open_the_Browser_Windows() {
        page.openBrowserWindow();
    }

    @And("open the 'Web Tables'")
    public void open_the_web_tables() {
        page.openWebTables();
    }

    @And("open the 'Progress Bar'")
    public void open_the_progress_bar() {
        page.openProgressBar();
    }

    @And("open the 'Sortable'")
    public void open_the_sortable() {
        page.openSortable();
    }
    
}
