package requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.TestSupport;
import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookRequests extends TestSupport {

    private Map<String, Object> setReservation(String userId, String... books) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);

        List<Map<String, Object>> collectionOfIsbns = new ArrayList<>();

        if (books != null) {
            for (String isbn : books) {
                Map<String, Object> isbnItem = new HashMap<>();
                isbnItem.put("isbn", isbn);
                
                collectionOfIsbns.add(isbnItem);
            }
        }

        payload.put("collectionOfIsbns", collectionOfIsbns);
        return payload;
    }
    
    public Response getAllBooks() {
        return RestAssured
            .given()
            .when()
            .get(Config.get("api.endpoint.books"))
            .andReturn();
    }
    
    public Response reservation(String userId, String username, String password, String... isbn) {
        String token = new UserRequests().authenticate(username, password).getBody().jsonPath().getString("token");
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .body(setReservation(userId, isbn))
            .when()
            .post(Config.get("api.endpoint.books"))
            .andReturn();
    }
    
    public Response clearReservations(String userId, String username, String password) {
        String token = new UserRequests().authenticate(username, password).getBody().jsonPath().getString("token");
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .queryParam("UserId", userId)
            .when()
            .delete(Config.get("api.endpoint.books"))
            .andReturn();
    }
}
