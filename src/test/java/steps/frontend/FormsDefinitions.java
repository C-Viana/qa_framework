package steps.frontend;

import java.util.Map;

import common.SeleniumUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.datafaker.Faker;
import objects.FormsObjects.gender;
import objects.FormsObjects.hobbies;
import pages.FormsPage;
import reporter.Reports;

public class FormsDefinitions {

    private final FormsPage page;
    private final Faker faker;

    public FormsDefinitions(FormsPage formsPage) {
        this.page = formsPage;
        this.faker = new Faker();
    }

    @When("I fill all fields with valid data")
    public void i_fill_all_fields_with_valid_data() {
        SeleniumUtils.validateElementIsDisplayed(page.getFirstname(), "Form is displayed");
        page.setFirstName(faker.name().firstName())
            .setLastName(faker.name().lastName())
            .setEmail(faker.internet().emailAddress())
            .setGender(gender.OTHER)
            .setPhoneNumber(faker.phoneNumber().cellPhone())
            .setBirthdate(18, 9, 1996)
            .setSubjects("English", "Biology")
            .setHobbies(hobbies.SPORTS)
            .setPicture(System.getProperty("user.dir")+"\\src\\test\\resources\\testingdata\\sample_text.txt")
            .setAddress(faker.address().fullAddress())
            .setState()
            .setCity();

        Reports.get().setInfo("Form filled", true);
        page.submit();
    }

    @Then("my data is confirmed on a modal window")
    public void my_data_is_confirmed_on_a_modal_window() {
        SeleniumUtils.validateElementIsDisplayed(page.getModalTable(), "Confirmation modal is displayed");
        Map<String,String> content = page.getConfirmationModalContent();
        for (String label : page.labels) {
            SeleniumUtils.validateTextNotBlank(content.get(label), label + " has valid value ["+content.get(label)+"]", false);
        }
    }
    
}
