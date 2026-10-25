package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import common.SeleniumUtils;
import engine.Driver;
import objects.SortableObjects;

public class SortablePage extends SortableObjects {

    public SortablePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void dragTo(WebElement elementoToMove, WebElement elementTarget) {
        new Actions(Driver.get()).dragAndDrop(elementoToMove, elementTarget).pause(1000).build().perform();
    }

    public void arrange(List<String> expectedOrder) {
        List<WebElement> initialOrder = SeleniumUtils.getAllElements(rowsSelector);

        SeleniumUtils.validateElementIsDisplayed(initialOrder.get(0), "Ordering elements are displayed");

        for (int i = 0; i < initialOrder.size()-1; i++) {
            if( !initialOrder.get(i).getText().equals(expectedOrder.get(i)) ) {
                for (WebElement current : initialOrder) {
                    if(current.getText().equals(expectedOrder.get(i))) {
                        dragTo(current, initialOrder.get(i));
                        initialOrder = SeleniumUtils.getAllElements(rowsSelector);
                        continue;
                    }
                }
            }
        }
    }

    public void validateArrangement(List<String> expectedOrder) {
        List<String> currentOrder = SeleniumUtils.getAllElements(rowsSelector).stream().map( x -> x.getText() ).toList();
        SeleniumUtils.validateEquals(true, expectedOrder.equals(currentOrder), "Elements are arranged according the expected order");
    }

}
