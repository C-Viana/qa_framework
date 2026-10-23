package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowserWindowsObjects {
    
    @FindBy( id = "tabButton" )
    protected WebElement buttonNewTab;
    
    @FindBy( id = "windowButton" )
    protected WebElement buttonNewWindow;
    
    @FindBy( id = "messageWindowButton" )
    protected WebElement buttonNewMessage;
    
    @FindBy( id = "sampleHeading" )
    protected WebElement textNewWindowMessage;

}
