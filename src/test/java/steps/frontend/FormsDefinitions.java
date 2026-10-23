package steps.frontend;

import java.util.Map;
import com.github.javafaker.Faker;

import common.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FormsPage;

public class FormsDefinitions extends FormsPage {

    @And("open the 'Practice Form'")
    public void open_the_practice_form() {
        BasePage.validateElementIsDisplayed(menuPracticeForm, "Validating menu option 'Practice Form'");
        openPracticeForm();
    }

    @When("I fill all fields with valid data")
    public void i_fill_all_fields_with_valid_data() {
        BasePage.validateElementIsDisplayed(inputFirstName, "Form is displayed");
        setFirstName(Faker.instance().name().firstName())
        .setLastName(Faker.instance().name().lastName())
        .setEmail(Faker.instance().internet().emailAddress())
        .setGender(gender.OTHER)
        .setPhoneNumber(Faker.instance().phoneNumber().cellPhone())
        .setBirthdate(18, 9, 1996)
        .setSubjects("English", "Biology")
        .setHobbies(hobbies.SPORTS)
        .setPicture(System.getProperty("user.dir")+"\\src\\test\\resources\\testingdata\\sample_text.txt")
        .setAddress(Faker.instance().address().fullAddress())
        .setState()
        .setCity();

        BasePage.reporter.setInfo("Form filled", true);
        submit();
    }

    @Then("my data is confirmed on a modal window")
    public void my_data_is_confirmed_on_a_modal_window() {
        BasePage.validateElementIsDisplayed(modalTableRows, "Confirmation modal is displayed");
        Map<String,String> content = getConfirmationModalContent();
        for (String label : labels) {
            BasePage.validateTextNotBlank(content.get(label), label + " has valid value ["+content.get(label)+"]", false);
        }
    }
    
}
