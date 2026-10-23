package runners;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import engine.TestBase;
import io.cucumber.core.options.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/frontend")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "steps/frontend")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@Run")
public class FrontendTestRunner implements TestBase {

    @BeforeSuite 
    static void setup() {
        TestBase.frontendStart();
    }

    @AfterSuite 
    static void shutdown() {
        TestBase.end();
    }
}
