package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.SeleniumUtils;
import engine.Driver;
import net.datafaker.Faker;
import objects.WebTablesObjects;
import reporter.Reports;

public class WebTablesPage extends WebTablesObjects {

    private static List<Map<String, String>> registries = new ArrayList<>();
    private final Faker faker;

    public WebTablesPage() {
        PageFactory.initElements(Driver.get(), this);
        this.faker = new Faker();
    }

    private void fillRegistry(int quantity) {
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            Map<String, String> reg = new HashMap<>();
            reg.put("First Name", faker.name().firstName());
            reg.put("Last Name", faker.name().lastName());
            reg.put("Email", faker.internet().emailAddress());
            reg.put("Age", String.valueOf(random.nextInt(50)+18));
            reg.put("Salary", String.valueOf(random.nextInt(8999)+1000));
            reg.put("Department", faker.company().industry());
            registries.add(reg);
        }
    }

    public WebTablesPage openNewRegistry() {
        SeleniumUtils.waitToBeVisible(buttonAdd, 30);
        buttonAdd.click();
        Reports.get().setInfo("Opening registry form", true);
        return this;
    }

    public WebTablesPage fillRandomData(int quantity) {
        SeleniumUtils.waitToBeVisible(buttonAdd, 30);
        fillRegistry(quantity);

        for (Map<String,String> reg : registries) {
            buttonAdd.click();
            inputFirstName.sendKeys(reg.get("First Name"));
            inputLastName.sendKeys(reg.get("Last Name"));
            inputEmail.sendKeys(reg.get("Email"));
            inputAge.sendKeys(reg.get("Age"));
            inputSalary.sendKeys(reg.get("Salary"));
            inputDepartment.sendKeys(reg.get("Department"));
            Reports.get().setInfo("Creating new registry for " + reg.get("Email"), true);
            buttonSubmit.click();
        }
        return this;
    }

    public void validateCreatedRegistries() {
        WebElement row = null;
        for (Map<String,String> reg : registries) {
            SeleniumUtils.waitToBeVisibleIgnoringExceptions(row, 5);
            row = SeleniumUtils.getElementByText(reg.get("Email"));

            while(SeleniumUtils.countElements(reg.get("Email")) < 1 && buttonNext.getAttribute("disabled") == null) {
                buttonNext.click();
                row = SeleniumUtils.getElementByText(reg.get("Email"));
            }

            try {
                SeleniumUtils.validateElementIsDisplayed(row, "Registry was created for user " + reg.get("Email"));
            } catch (Exception e) {
                Reports.get().setFail("Registry not created for e-mail " + reg.get("Email"), true);
                throw new AssertionError("Registry not created for e-mail " + reg.get("Email"));
            }
        }
    }

    public void deleteCreatedRegistries() {
        for (Map<String,String> reg : registries) {
            inputSearch.clear();
            inputSearch.sendKeys(reg.get("Email"));
            deleteRegistry();
        }
        int totalElements = tableContent.findElements(By.tagName("tr")).size();
        SeleniumUtils.validateEquals(3, totalElements, "All new elements were deleted");
    }

    public void searchRegistry(String searchValue) {
        inputSearch.clear();
        inputSearch.sendKeys(searchValue);
        WebElement row = Driver.get().findElement(By.xpath("//td[text()='"+searchValue+"']"));
        SeleniumUtils.validateElementIsDisplayed(row, "Registry found for value " + searchValue);
    }

    public void editRegistry(Map<String, String> reg) {
        buttonEdit.click();

        inputFirstName.clear();
        inputFirstName.sendKeys(reg.get("First Name"));

        inputLastName.clear();
        inputLastName.sendKeys(reg.get("Last Name"));
        
        inputEmail.clear();
        inputEmail.sendKeys(reg.get("Email"));
        
        inputAge.clear();
        inputAge.sendKeys(reg.get("Age"));
        
        inputSalary.clear();
        inputSalary.sendKeys(reg.get("Salary"));
        
        inputDepartment.clear();
        inputDepartment.sendKeys(reg.get("Department"));
        Reports.get().setInfo("Creating new registry", true);

        buttonSubmit.click();
    }

    public void validateRegistryFields(Map<String, String> reg) {
        WebElement row = Driver.get().findElement(By.xpath("//td[text()='"+reg.get("Email")+"']"));
        SeleniumUtils.validateElementIsDisplayed(row, "Registry found for value " + reg.get("Email"));
        List<String> values = row.findElements(By.tagName("td")).stream().map( x -> x.getText()).toList();
        SeleniumUtils.validateEquals(true, reg.values().containsAll(values), "All fields match the changes performed");
    }

    public void deleteRegistry() {
        SeleniumUtils.waitToBeVisibleIgnoringExceptions(buttonDelete, 5);
        buttonDelete.click();
    }

    public void validateRegistryIsMissing(String searchValue) {
        inputSearch.clear();
        inputSearch.sendKeys(searchValue);
        int totalElements = Driver.get().findElements(By.xpath("//td[text()='"+searchValue+"']")).size();
        SeleniumUtils.validateEquals(0, totalElements, "Element was deleted");
    }

}
