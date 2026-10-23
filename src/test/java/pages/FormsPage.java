package pages;

import objects.FormsObjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import engine.Driver;

public class FormsPage extends FormsObjects {

    protected List<String> labels = Arrays.asList(
            "Student Name",
            "Student Email",
            "Gender",
            "Mobile",
            "Date of Birth",
            "Subjects",
            "Hobbies",
            "Picture",
            "Address",
            "State and City"
        );
    
    public FormsPage() {
        PageFactory.initElements( Driver.get(), this );
    }

    public void openPracticeForm() {
        menuPracticeForm.click();
    }

    public FormsPage setFirstName(String name) {
        if(name.isBlank()) throw new AssertionError("Name must not be null or blank");
        inputFirstName.sendKeys(name);
        return this;
    }

    public FormsPage setLastName(String name) {
        if(name.isBlank()) throw new AssertionError("Last name must not be null or blank");
        inputLastName.sendKeys(name);
        return this;
    }

    public FormsPage setEmail(String email) {
        if(email.isBlank()) throw new AssertionError("Email must not be null or blank");
        inputEmail.sendKeys(email);
        return this;
    }

    public FormsPage setGender(gender option) {
        switch (option) {
            case MALE:
                radioGender.get(0).click();
                break;
            case FEMALE:
                radioGender.get(1).click();
                break;
            case OTHER:
                radioGender.get(2).click();
                break;
            default:
                break;
        }
        return this;
    }

    public FormsPage setPhoneNumber(String number) {
        if(number.isBlank() || number.matches("\\D")) throw new AssertionError("Phone number must not be null, blank or have values other than numbers");
        String formattedNumber = number.replaceAll("\\D", "");
        inputPhoneNumber.sendKeys(formattedNumber);
        return this;
    }

    public FormsPage setBirthdate(int day, int month, int year) {
        inputBirthdate.click();
        BasePage.waitFor(1);
        BasePage.selectByText(selectBirthdateYear, String.valueOf(year));
        BasePage.selectByValue(selectBirthdateMonth, String.valueOf(month-1));
        tableBirthdateDay.findElement(By.xpath("//div[@class='react-datepicker__month']//div[text()='"+day+"']")).click();

        return this;
    }

    public FormsPage setSubjects(String... options) {
        for (String opt : options) {
            inputSubjects.click();
            inputSubjects.sendKeys(opt.trim());
            BasePage.waitFor(1);
            inputSubjectsSelection.click();
        }
        return this;
    }

    public FormsPage setHobbies(hobbies... options) {
        for (hobbies hobbie : options) {
            switch (hobbie) {
                case SPORTS:
                    checkboxHobbies.get(0).click();
                    break;
                case READING:
                    checkboxHobbies.get(1).click();
                    break;
                case MUSIC:
                    checkboxHobbies.get(2).click();
                    break;
                default:
                    break;
            }
        }
        return this;
    }

    public FormsPage setPicture(String path) {
        if(path.isBlank()) throw new AssertionError("File path must not be null or blank");
        inputFile.sendKeys(path);
        return this;
    }

    public FormsPage setAddress(String address) {
        BasePage.scrollBottomPage();
        if(address.isBlank()) throw new AssertionError("Address must not be null or blank");
        textareaAddress.sendKeys(address);
        return this;
    }

    public FormsPage setState() {
        selectState.click();
        BasePage.waitFor(1);
        int listSize = selectStateOptions.size();
        selectStateOptions.get(
                new Random().nextInt(listSize)
            ).click();
        return this;
    }

    public FormsPage setCity() {
        selectCity.click();
        BasePage.waitFor(1);
        int listSize = selectCityOptions.size();
        selectCityOptions.get(
                new Random().nextInt(listSize)
            ).click();
        return this;
    }

    public void submit() {
        buttonSubmit.click();
    }

    public Map<String,String> getConfirmationModalContent() {
        Map<String, String> finalValues = new HashMap<>();
        for (int i = 0; i < labels.size(); i++) {
            finalValues.put(
                labels.get(i), 
                modalTableRows.findElement(By.cssSelector("tr:nth-child("+(i+1)+") > td:nth-child(2)")).getText());
        }
        return finalValues;
    }
    
}
