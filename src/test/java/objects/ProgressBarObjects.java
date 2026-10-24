package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProgressBarObjects {
    
    @FindBy( css = "#progressBar > div" )
    protected WebElement progressBar;
    
    @FindBy( id = "startStopButton" )
    protected WebElement buttonStart;
    
    @FindBy( id = "resetButton" )
    protected WebElement buttonReset;

}
