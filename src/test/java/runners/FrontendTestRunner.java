package runners;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.BeforeSuite;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import config.Config;
import io.cucumber.core.options.Constants;
import reporter.Reports;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/frontend")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "steps/frontend")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html")
// @ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@TestForms01")
public class FrontendTestRunner {

    @BeforeSuite 
    static void setup() {
        Reports.get().setEnvironmentValue(Config.get("test.environment"));
    }

    @AfterSuite 
    static void shutdown() {
        Reports.get().endReport();
    }
}
