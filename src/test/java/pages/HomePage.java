package pages;

import objects.HomeObjects;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import engine.Driver;

public class HomePage extends HomeObjects {
    
    public HomePage() {
        PageFactory.initElements( Driver.get(), this );
    }

    public void openPracticeForm() {
        menuPracticeForm.click();
    }

    public void openBrowserWindow() {
        menuBrowserWindow.click();
    }

    public void openWebTables() {
        menuWebTables.click();
    }

    public WebElement getCategory(categories category) {
        WebElement selected = null;

        switch (category) {
            case categories.ELEMENTS:
                selected = categoryCardElements;
                break;
            case categories.FORMS:
                selected = categoryCardForms;
                break;
            case categories.ALERTS:
                selected = categoryCardAlerts;
                break;
            case categories.WIDGETS:
                selected = categoryCardWidgets;
                break;
            case categories.INTERACTIONS:
                selected = categoryCardInteraction;
                break;
            case categories.BOOKS:
                selected = categoryCardBooks;
                break;
            default:
                throw new InvalidArgumentException("INVALID CATEGORY ["+category+"]");
        }

        return selected;
    }
    
}
