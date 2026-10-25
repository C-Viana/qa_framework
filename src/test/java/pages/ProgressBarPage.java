package pages;

import java.time.LocalDateTime;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.support.PageFactory;

import common.SeleniumUtils;
import engine.Driver;
import objects.ProgressBarObjects;
import reporter.Reports;

public class ProgressBarPage extends ProgressBarObjects {

    private static Integer progress = 0;
    private static LocalDateTime startTime = null;

    public ProgressBarPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    private void runProgressCheck(Integer breakpoint) {
        startTime = LocalDateTime.now();
        while (progress < breakpoint && SeleniumUtils.timer(startTime, startTime.plusMinutes(1))) {
            progress = Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
        }
    }

    public void startProgressBar() {
        SeleniumUtils.validateEquals("Start", buttonStart.getText(), "Button Start is set");
        buttonStart.click();
    }

    public void stopProgressBarAt(Integer min, Integer max) {
        if(max < min || max < 1 || min < 0) {
            Reports.get().setFail("Progress interval is invalid: min {"+min+"} | max {"+max+"}");
            throw new InvalidArgumentException("Progress interval is invalid: min {"+min+"} | max {"+max+"}");
        }

        progress = 0;
        runProgressCheck(min);

        SeleniumUtils.validateEquals("Stop", buttonStart.getText(), "Button stop is set");
        buttonStart.click();
    }

    public void validateProgressBarAt(Integer min, Integer max) {
        boolean result = progress >= min && progress <= max;
        SeleniumUtils.validateEquals(true, result, "Progress bar stopped successfully between range "+min+"-"+max+"% with value "+progress);
    }

    public void validateProgressBarCompleted() {
        runProgressCheck(100);
        boolean result = progress == 100;
        SeleniumUtils.validateEquals(true, result, "Progress bar stopped successfully at 100%");
    }

    public void resetProgressBar() {
        SeleniumUtils.validateElementIsDisplayed(buttonReset, "Reset button is set");
        buttonReset.click();
        SeleniumUtils.validateElementIsDisplayed(buttonStart, "Start button is set again");
        progress = Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
        SeleniumUtils.validateEquals(0, progress, "Progress bar is reseted to 0%");
    }

}
