package steps.backend.books;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import reporter.Reports;
import requests.BookRequests;
import steps.backend.ScenarioContext;
import tools.jackson.databind.node.ArrayNode;

public class BooksDefinition {
    private final BookRequests bookRequests;
    private final ScenarioContext ctx;

    public BooksDefinition(BookRequests bookRequests, ScenarioContext ctx) {
        this.bookRequests = bookRequests;
        this.ctx = ctx;
    }

    @Given("I request all books showcase")
    public void i_request_all_books_showcase() {
        ctx.response = bookRequests.getAllBooks();
        ctx.responseBody = bookRequests.toJson(ctx.response.asString());
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
    }

    @And("a list of all books will be returned")
    public void a_list_of_all_books_will_be_returned() {
        bookRequests.validateEquals(true, ctx.responseBody.get("books").isArray(), "Response is an array");
        int booksCount = ctx.responseBody.get("books").asArray().size();
        bookRequests.validateEquals(true, booksCount > 0, "List of products exists and has content. Total books is " + booksCount);
    }

    @Given("I have a valid user ID")
    public void i_have_a_valid_user_id() {
        bookRequests.validateEquals(false, ctx.userId.isBlank(), "User ID is set: " + ctx.userId);
    }

    @When("I request a reservation for the book {string}")
    public void i_request_a_reservation_for_the_book(String isbn) {
        ctx.response = bookRequests.reservation(ctx.userId, ctx.username, ctx.password, isbn);
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
        ctx.responseBody = bookRequests.toJson(ctx.response.asString());
    }

    @And("the required books are listed in the response")
    public void the_required_books_are_listed_in_the_response() {
        ArrayNode bookCode = ctx.responseBody.get("books").asArray();
        bookRequests.validateEquals(false, bookCode.has("isbn"), "Book ISBN is reserved");
    }

    @When("I remove reservation for user ID")
    public void i_remove_reservation_for_user_id() {
        ctx.response = bookRequests.clearReservations(ctx.userId, ctx.username, ctx.password);
        Reports.get().setInfo("RESPONSE:\n"+ctx.response.asPrettyString());
        ctx.responseBody = bookRequests.toJson(ctx.response.asString());
    }
    
}
