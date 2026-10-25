package reporter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import common.FilesSupport;
import engine.Driver;

public class ExtentReportsImpl implements ReporterInterface {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports reporter;
    private ExtentTest test;
    private String ROOT_PATH = "";
    private String ENVIRONMENT = "";

    @Override
    public void setResultPath(String scenarioname) {
        ROOT_PATH = "src/test/results/" + scenarioname;
        FilesSupport.createFolders(ROOT_PATH);
    }

    @Override
    public void setEnvironmentValue(String environment) {
        ENVIRONMENT = environment;
    }

    @Override
    public void startReport() {
        sparkReporter = new ExtentSparkReporter(ROOT_PATH + "/Results.html");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setDocumentTitle("Execution Summary");
        sparkReporter.config().setTheme(Theme.STANDARD);

        reporter = new ExtentReports();
        reporter.attachReporter(sparkReporter);
        reporter.setSystemInfo("OS", System.getProperty("os.name"));
        reporter.setSystemInfo("Environment", ENVIRONMENT);
    }

    @Override
    public void startTest(String testName) {
        if(reporter == null)
            startReport();
        test = reporter.createTest(testName);
    }

    @Override
    public void endReport() {
        reporter.flush();
    }

    @Override
    public void setStep(Status statusValue, String stepLog) {
        test.log( statusValue, stepLog );
    }

    @Override
    public void setPass(String stepLog) {
        setPass(stepLog, false);
    }

    @Override
    public void setFail(String stepLog) {
        setFail(stepLog, false);
    }

    @Override
    public void setInfo(String stepLog) {
        setInfo(stepLog, false);
    }

    @Override
    public void setPass(String stepLog, boolean screenshot) {
        if(screenshot)
            test.log( Status.PASS, stepLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getShot()).build() );
        else
            test.log( Status.PASS, stepLog );
    }

    @Override
    public void setFail(String stepLog, boolean screenshot) {
        if(screenshot)
            test.log( Status.FAIL, stepLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getShot()).build() );
        else
            test.log( Status.FAIL, stepLog );
    }

    @Override
    public void setInfo(String stepLog, boolean screenshot) {
        if(screenshot)
            test.log( Status.INFO, stepLog, MediaEntityBuilder.createScreenCaptureFromBase64String(getShot()).build() );
        else
            test.log( Status.INFO, stepLog );
    }

    private static String getShot() {
        return ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BASE64);
    }

}
