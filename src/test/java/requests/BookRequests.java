package requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.TestSupport;
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
    
    protected Response getAllBooks() {
        return RestAssured
            .given()
            .when()
            .get(properties.getProperty("api.endpoint.books"))
            .andReturn();
    }
    
    protected Response reservation(String userId, String... isbn) {
        String token = new UserRequests().authenticate("Miss Dimple Lowe", "s#NX6v76!J6VF").getBody().jsonPath().getString("token");
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .body(setReservation(userId, isbn))
            .when()
            .post(properties.getProperty("api.endpoint.books"))
            .andReturn();
    }
    
    protected Response clearReservations(String userId) {
        String token = new UserRequests().authenticate("Miss Dimple Lowe", "s#NX6v76!J6VF").getBody().jsonPath().getString("token");
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .queryParam("UserId", userId)
            .when()
            .delete(properties.getProperty("api.endpoint.books"))
            .andReturn();
    }
}
