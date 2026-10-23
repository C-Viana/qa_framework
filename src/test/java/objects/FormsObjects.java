package objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormsObjects {

    public static enum gender {
        MALE, FEMALE, OTHER
    }

    public static enum hobbies {
        SPORTS, READING, MUSIC
    }
    
    @FindBy( linkText = "Practice Form" )
    protected WebElement menuPracticeForm;
    
    @FindBy( id = "firstName" )
    protected WebElement inputFirstName;
    
    @FindBy( id = "lastName" )
    protected WebElement inputLastName;
    
    @FindBy( id = "userEmail" )
    protected WebElement inputEmail;
    
    @FindBy( css = "input[type='radio']" )
    protected List<WebElement> radioGender;
    
    @FindBy( id = "userNumber" )
    protected WebElement inputPhoneNumber;
    
    @FindBy( id = "dateOfBirthInput" )
    protected WebElement inputBirthdate;
    
    @FindBy( className = "react-datepicker__year-select" )
    protected WebElement selectBirthdateYear;
    
    @FindBy( className = "react-datepicker__month-select" )
    protected WebElement selectBirthdateMonth;
    
    @FindBy( className = "react-datepicker__month" )
    protected WebElement tableBirthdateDay;
    
    @FindBy( css = "#subjectsContainer input" )
    protected WebElement inputSubjects;
    
    @FindBy( css = "div[role='listbox'] > div" )
    protected WebElement inputSubjectsSelection;
    
    @FindBy( css = "div#hobbiesWrapper input" )
    protected List<WebElement> checkboxHobbies;
    
    @FindBy( id = "uploadPicture" )
    protected WebElement inputFile;
    
    @FindBy( css = "div#currentAddress-wrapper textarea" )
    protected WebElement textareaAddress;
    
    @FindBy( id = "react-select-3-input" )
    protected WebElement selectState;
    
    @FindBy( css = "div#state div[role='listbox'] > div" )
    protected List<WebElement> selectStateOptions;
    
    @FindBy( id = "react-select-4-input" )
    protected WebElement selectCity;
    
    @FindBy( css = "div#city div[role='listbox'] > div" )
    protected List<WebElement> selectCityOptions;
    
    @FindBy( id = "submit" )
    protected WebElement buttonSubmit;
    
    @FindBy( css = "div.modal-content table > tbody" )
    protected WebElement modalTableRows;

}
