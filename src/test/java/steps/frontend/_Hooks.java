package steps.frontend;

import engine.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import reporter.Reports;

public class _Hooks {

    @Before
    public void startTest(Scenario scenario) {
        Driver.get();
        Reports.get().setResultPath("frontend");
        Reports.get().startTest(scenario.getName());
    }

    @After
    public void endTest(Scenario scenario) {
        if (scenario.isFailed())
            Reports.get().setFail("Test failed", true);
        Driver.close();
    }
    
}
