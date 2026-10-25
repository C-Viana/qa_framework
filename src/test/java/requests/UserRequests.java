package requests;

import java.util.HashMap;
import java.util.Map;

import common.TestSupport;
import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserRequests extends TestSupport {

    private Map<String, String> setCredentials(String username, String password) {
        return new HashMap<>(Map.of(
            "userName", username,
            "password", password
        ));
    }

    public Response create(String username, String password) {
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(setCredentials(username, password))
            .when()
            .post(Config.get("api.endpoint.account.user"))
            .andReturn();
    }

    public Response authenticate(String username, String password) {
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(setCredentials(username, password))
            .when()
            .post(Config.get("api.endpoint.account.token"))
            .andReturn();
    }

    public Response authorization(String username, String password) {
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(setCredentials(username, password))
            .when()
            .post(Config.get("api.endpoint.account.authorized"))
            .andReturn();
    }

    public Response reservation(String userId, String username, String password) {
        String endpoint = Config.get("api.endpoint.account.user")+"/"+userId;

        String token = new UserRequests().authenticate(username, password).getBody().jsonPath().getString("token");

        return RestAssured
            .given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
            .when()
            .get(endpoint)
            .andReturn();
    }

}
