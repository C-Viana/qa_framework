package steps.frontend;

import common.BasePage;
import io.cucumber.java.en.Given;
import pages.HomePage;

public class HomeDefinitions extends HomePage {

    @Given("I navigate to {string}")
    public void i_navigate_to(String category) {
        BasePage.validateElementIsDisplayed(categoryCardForms, "Card "+category+" is displayed");
        getCategory( categories.valueOf(category.toUpperCase()) ).click();
    }
    
}
