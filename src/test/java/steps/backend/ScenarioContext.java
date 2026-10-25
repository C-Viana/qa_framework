package steps.backend;

import io.restassured.response.Response;
import tools.jackson.databind.JsonNode;

public class ScenarioContext {
    public static String username;
    public static String password;
    public static String userId;
    public static String token;
    public static Response response;
    public static JsonNode responseBody;
}
