package engine;

import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import reporter.ExtentReportsImpl;
import reporter.ReporterInterface;
import runners.BackendTestRunner;

public interface TestBase {
    Properties properties = new Properties();
    ReporterInterface reporter = new ExtentReportsImpl();
    public static Map<String, Object> testData = new HashMap<>();

    public static void put(String key, String value) {
        testData.put(key, value);
    }

    public static Object get(String key) {
        return testData.get(key);
    }

    public static void startProperties() {
        try (InputStream input = BackendTestRunner.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reporter.setEnvironmentValue(properties.getProperty("test.environment"));
    }

    public static void backendStart() {
        startProperties();
        long timetouAmount = Long.parseLong(properties.getProperty("test.timeout.default"));
        Duration timeoutSpec = Duration.ofSeconds(timetouAmount);
        RestAssured.baseURI = properties.getProperty("application.host");
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(timeoutSpec.toNanos()));
        RestAssured.responseSpecification = resBuilder.build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static void frontendStart() {
        TestBase.startProperties();
        reporter.setEnvironmentValue(properties.getProperty("test.environment"));
    }

    public static void end() {
        reporter.endReport();
    }
}
