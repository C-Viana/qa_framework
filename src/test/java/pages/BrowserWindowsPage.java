package pages;

import org.openqa.selenium.support.PageFactory;

import common.SeleniumUtils;
import engine.Driver;
import objects.BrowserWindowsObjects;

public class BrowserWindowsPage extends BrowserWindowsObjects {

    public BrowserWindowsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void openNewWindow() {
        SeleniumUtils.waitToBeVisibleIgnoringExceptions(buttonNewWindow, 20);
        buttonNewWindow.click();
    }

    public void changeWindow(int index) {
        SeleniumUtils.changeWindow(index);
    }

    public void validateSampleMessage(String expectedMessage) {
        String currentText = textNewWindowMessage.getText();
        SeleniumUtils.validateEquals(expectedMessage, currentText, "Validating message matches");
        SeleniumUtils.returnToMainWindow();
    }

}
