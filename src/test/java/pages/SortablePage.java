package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import engine.Driver;
import objects.SortableObjects;

public class SortablePage extends SortableObjects {

    public SortablePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void dragTo(WebElement elementoToMove, WebElement elementTarget) {
        new Actions(Driver.get()).dragAndDrop(elementoToMove, elementTarget).build().perform();
        BasePage.waitFor(1);
    }

    public void arrange(List<String> expectedOrder) {
        BasePage.waitFor(10);
        List<WebElement> initialOrder = BasePage.getAllElements(rowsSelector);

        BasePage.validateElementIsDisplayed(initialOrder.get(0), "Ordering elementes are displayed");

        for (int i = 0; i < initialOrder.size()-1; i++) {
            if( !initialOrder.get(i).getText().equals(expectedOrder.get(i)) ) {
                for (WebElement current : initialOrder) {
                    if(current.getText().equals(expectedOrder.get(i))) {
                        dragTo(current, initialOrder.get(i));
                        initialOrder = BasePage.getAllElements(rowsSelector);
                        continue;
                    }
                }
            }
        }
    }

    public void validateArrangement(List<String> expectedOrder) {
        List<String> currentOrder = BasePage.getAllElements(rowsSelector).stream().map( x -> x.getText() ).toList();
        BasePage.validateEquals(true, expectedOrder.equals(currentOrder), "Elements are arranged according the expected order");
    }

}
