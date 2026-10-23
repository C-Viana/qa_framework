package steps.frontend;

import common.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.HomePage;

public class HomeDefinitions extends HomePage {

    @Given("I navigate to {string}")
    public void i_navigate_to(String category) {
        BasePage.validateElementIsDisplayed(categoryCardForms, "Card "+category+" is displayed");
        getCategory( categories.valueOf(category.toUpperCase()) ).click();
    }

    @And("open the 'Practice Form'")
    public void open_the_practice_form() {
        BasePage.validateElementIsDisplayed(menuPracticeForm, "Validating menu option 'Practice Form'");
        openPracticeForm();
    }

    @And("open the 'Browser Windows'")
    public void open_the_Browser_Windows() {
        BasePage.validateElementIsDisplayed(menuBrowserWindow, "Validating menu option 'Practice Form'");
        openBrowserWindow();
    }

    @And("open the 'Web Tables'")
    public void open_the_web_tables() {
        BasePage.validateElementIsDisplayed(menuWebTables, "Validating menu option 'Web Tables'");
        openWebTables();
    }
    
}
