package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;

import common.BasePage;
import engine.Driver;
import objects.WebTablesObjects;

public class WebTablesPage extends WebTablesObjects {

    private static List<Map<String, String>> registries = new ArrayList<>();

    public WebTablesPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    private void fillRegistry(int quantity) {
        Faker faker = Faker.instance();
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

    protected WebTablesPage openNewRegistry() {
        BasePage.waitToBeVisible(buttonAdd, 30);
        buttonAdd.click();
        BasePage.reporter.setInfo("Opening registry form", true);
        return this;
    }

    protected WebTablesPage fillRandomData(int quantity) {
        BasePage.waitToBeVisible(buttonAdd, 30);
        fillRegistry(quantity);

        for (Map<String,String> reg : registries) {
            buttonAdd.click();
            inputFirstName.sendKeys(reg.get("First Name"));
            inputLastName.sendKeys(reg.get("Last Name"));
            inputEmail.sendKeys(reg.get("Email"));
            inputAge.sendKeys(reg.get("Age"));
            inputSalary.sendKeys(reg.get("Salary"));
            inputDepartment.sendKeys(reg.get("Department"));
            BasePage.reporter.setInfo("Creating new registry for " + reg.get("Email"), true);
            buttonSubmit.click();
        }
        return this;
    }

    protected void validateCreatedRegistries() {
        WebElement row = null;
        for (Map<String,String> reg : registries) {
            row = BasePage.getElementByText(reg.get("Email"));

            while(BasePage.countElements(reg.get("Email")) < 1) {
                buttonNext.click();
                BasePage.waitFor(1);
                row = BasePage.getElementByText(reg.get("Email"));
            }

            try {
                BasePage.validateElementIsDisplayed(row, "Registry was created for user " + reg.get("Email"));
            } catch (Exception e) {
                BasePage.reporter.setFail("Registry not created for e-mail " + reg.get("Email"), true);
                throw new AssertionError("Registry not created for e-mail " + reg.get("Email"));
            }
        }
    }

    protected void deleteCreatedRegistries() {
        for (Map<String,String> reg : registries) {
            inputSearch.clear();
            inputSearch.sendKeys(reg.get("Email"));
            deleteRegistry();
        }
        int totalElements = tableContent.findElements(By.tagName("tr")).size();
        BasePage.validateEquals(3, totalElements, "All new elements were deleted");
    }

    protected void searchRegistry(String searchValue) {
        inputSearch.clear();
        inputSearch.sendKeys(searchValue);
        WebElement row = Driver.get().findElement(By.xpath("//td[text()='"+searchValue+"']"));
        BasePage.validateElementIsDisplayed(row, "Registry found for value " + searchValue);
    }

    protected void editRegistry(Map<String, String> reg) {
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
        BasePage.reporter.setInfo("Creating new registry", true);

        buttonSubmit.click();
    }

    protected void validateRegistryFields(Map<String, String> reg) {
        WebElement row = Driver.get().findElement(By.xpath("//td[text()='"+reg.get("Email")+"']"));
        BasePage.validateElementIsDisplayed(row, "Registry found for value " + reg.get("Email"));
        List<String> values = row.findElements(By.tagName("td")).stream().map( x -> x.getText()).toList();
        BasePage.validateEquals(true, reg.values().containsAll(values), "All fields match the changes performed");
    }

    protected void deleteRegistry() {
        BasePage.waitFor(1);
        buttonDelete.click();
    }

    protected void validateRegistryIsMissing(String searchValue) {
        inputSearch.clear();
        inputSearch.sendKeys(searchValue);
        int totalElements = Driver.get().findElements(By.xpath("//td[text()='"+searchValue+"']")).size();
        BasePage.validateEquals(0, totalElements, "Element was deleted");
    }

}
