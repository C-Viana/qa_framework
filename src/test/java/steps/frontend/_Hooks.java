package steps.frontend;

import engine.Driver;
import engine.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class _Hooks implements TestBase {

    @Before
    public void startTest(Scenario scenario) {
        Driver.get();
        reporter.startTest(scenario.getName());
    }

    @After
    public void endTest() {
        Driver.close();
    }
    
}
