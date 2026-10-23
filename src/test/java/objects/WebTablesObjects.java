package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebTablesObjects {
    
    @FindBy( id = "addNewRecordButton" )
    protected WebElement buttonAdd;
    
    @FindBy( id = "searchBox" )
    protected WebElement inputSearch;
    
    @FindBy( css = "div.web-tables-wrapper > table > tbody" )
    protected WebElement tableContent;
    
    @FindBy( id = "firstName" )
    protected WebElement inputFirstName;
    
    @FindBy( id = "lastName" )
    protected WebElement inputLastName;
    
    @FindBy( id = "userEmail" )
    protected WebElement inputEmail;
    
    @FindBy( id = "age" )
    protected WebElement inputAge;
    
    @FindBy( id = "salary" )
    protected WebElement inputSalary;
    
    @FindBy( id = "department" )
    protected WebElement inputDepartment;
    
    @FindBy( id = "submit" )
    protected WebElement buttonSubmit;
    
    @FindBy( xpath = "//button[text()='Next']" )
    protected WebElement buttonNext;
    
    @FindBy( css = "span[title='Edit']" )
    protected WebElement buttonEdit;
    
    @FindBy( css = "span[title='Delete']" )
    protected WebElement buttonDelete;

}
