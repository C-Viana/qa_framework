package pages;

import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import engine.Driver;
import objects.BrowserWindowsObjects;

public class BrowserWindowsPage extends BrowserWindowsObjects {

    public BrowserWindowsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    protected void openNewWindow() {
        BasePage.waitFor(3);
        buttonNewWindow.click();
    }

    protected void changeWindow(int index) {
        BasePage.changeWindow(index);
    }

    protected void validateSampleMessage(String expectedMessage) {
        String currentText = textNewWindowMessage.getText();
        BasePage.validateEquals(expectedMessage, currentText, "Validating message matches");
        BasePage.returnToMainWindow();
    }

}
