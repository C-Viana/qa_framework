package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortableObjects {
    
    @FindBy( css = ".list-group > div" )
    protected WebElement rows;

    protected By rowsSelector = By.cssSelector(".list-group > div");

}
