package pages;

import objects.HomeObjects;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.SeleniumUtils;
import engine.Driver;

public class HomePage extends HomeObjects {
    
    public HomePage() {
        PageFactory.initElements( Driver.get(), this );
    }

    public WebElement getCategory(categories category) {
        SeleniumUtils.validateElementIsDisplayed(categoryCardForms, "Card "+category+" is displayed");
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

    public void openPracticeForm() {
        SeleniumUtils.validateElementIsDisplayed(menuPracticeForm, "Selecting menu option 'Practice Form'");
        menuPracticeForm.click();
    }

    public void openBrowserWindow() {
        SeleniumUtils.validateElementIsDisplayed(menuBrowserWindow, "Selecting menu option 'Practice Form'");
        menuBrowserWindow.click();
    }

    public void openWebTables() {
        SeleniumUtils.validateElementIsDisplayed(menuWebTables, "Selecting menu option 'Web Tables'");
        menuWebTables.click();
    }

    public void openProgressBar() {
        SeleniumUtils.validateElementIsDisplayed(menuProgressBar, "Selecting menu option 'Progress Bar'");
        menuProgressBar.click();
    }

    public void openSortable() {
        SeleniumUtils.validateElementIsDisplayed(menuSortable, "Selecting menu option 'Sortable'");
        menuSortable.click();
    }
    
}
