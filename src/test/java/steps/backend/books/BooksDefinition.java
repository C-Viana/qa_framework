package steps.backend.books;

import engine.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import requests.BookRequests;
import tools.jackson.databind.node.ArrayNode;

public class BooksDefinition extends BookRequests {
    private String userId;

    @Before
    void startCase() {
        reporter.startTest("Book Scenario");
    }

    @After
    void finishCase() {
        response = null;
        responseBody = null;
    }

    @Given("I request all books showcase")
    public void i_request_all_books_showcase() {
        response = getAllBooks();
        responseBody = toJson(response.asString());
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
    }

    @And("a list of all books will be returned")
    public void a_list_of_all_books_will_be_returned() {
        validateEquals(true, responseBody.get("books").isArray(), "Response is an array");
        int booksCount = responseBody.get("books").asArray().size();
        validateEquals(true, booksCount > 0, "List of products exists and has content. Total books is " + booksCount);
    }

    @Given("I have a valid user ID")
    public void i_have_a_valid_user_id() {
        //TODO: parametrizar user ID
        userId = "89519514-630f-4a89-ad86-80340b9b2da1";
        TestBase.put("userId", userId);
        validateEquals(false, userId.isBlank(), "User ID is set: " + userId);
    }

    @When("I request a reservation for the book {string}")
    public void i_request_a_reservation_for_the_book(String isbn) {
        response = reservation(userId, isbn);
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
        responseBody = toJson(response.asString());
    }

    @And("the required books are listed in the response")
    public void the_required_books_are_listed_in_the_response() {
        ArrayNode bookCode = responseBody.get("books").asArray();
        validateEquals(false, bookCode.has("isbn"), "Book ISBN is reserved");
    }

    @When("I remove reservation for user ID {string}")
    public void i_remove_reservation_for_user_id(String userId) {
        response = clearReservations(userId);
        reporter.setInfo("RESPONSE:\n"+response.asPrettyString());
        responseBody = toJson(response.asString());
    }
    
}
