package engine;

import java.time.Duration;

import org.hamcrest.Matchers;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;

public final class ApiSetup {
    
    public static void configure() {
        RestAssured.baseURI = Config.get("application.host");
        RestAssured.responseSpecification = new ResponseSpecBuilder()
            .expectResponseTime(
                Matchers.lessThan(Duration.ofSeconds(Config.getInt("test.timeout.default")).toMillis())
                )
            .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
