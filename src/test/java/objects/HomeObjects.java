package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeObjects {

    public static enum categories {
        ELEMENTS,
        FORMS,
        ALERTS,
        WIDGETS,
        INTERACTIONS,
        BOOKS;
    }
    
    @FindBy( css = "a[href='/elements']" )
    protected WebElement categoryCardElements;
    
    @FindBy( css = "a[href='/forms']" )
    protected WebElement categoryCardForms;
    
    @FindBy( css = "a[href='/alertsWindows']" )
    protected WebElement categoryCardAlerts;
    
    @FindBy( css = "a[href='/widgets']" )
    protected WebElement categoryCardWidgets;
    
    @FindBy( css = "a[href='/interaction']" )
    protected WebElement categoryCardInteraction;
    
    @FindBy( css = "a[href='/books']" )
    protected WebElement categoryCardBooks;

}
