package runners;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import config.Config;
import engine.ApiSetup;
import io.cucumber.core.options.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import reporter.Reports;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/backend")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "steps/backend")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html")
// @ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@TestBackendBook01")
public class BackendTestRunner {

    @BeforeSuite 
    static void setup() {
        Reports.get().setEnvironmentValue(Config.get("test.environment"));
        Reports.get().setResultPath("backend");
        ApiSetup.configure();
    }

    @Before
    void startCase(Scenario scenario) {
        Reports.get().startTest(scenario.getName());
    }

    @After
    public void endTest(Scenario scenario) {
        if (scenario.isFailed())
            Reports.get().setFail("Test failed", true);
    }

    @AfterSuite 
    static void shutdown() {
        Reports.get().endReport();
    }
}
