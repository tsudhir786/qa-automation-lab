package com.tirumareddy.qa.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ExpenseApiTest {

    @Test
    void shouldGetExpenses() {

        given()
                .baseUri("http://localhost:8080")
                .log().all()

                .when()
                .get("/api/expenses")

                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    void shouldCreateAndGetExpense() {
        String requestBody = """
                {
                "description": "Interview Coffee",
                "amount": 180.00,
                "categoryId": 1
                }
                """;

        Integer expenseId =
                given()
                        .baseUri("http://localhost:8080")
                        .contentType("application/json")
                        .body(requestBody)
                        .log()
                        .all()

                        .when()
                        .post("/api/expenses")

                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("description", equalTo("Interview Coffee"))
                        .extract()
                        .path("id");

        given()
                .baseUri("http://localhost:8080")
                .pathParams("id", expenseId)

                .when()
                .get("/api/expenses/{id}")

                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(expenseId))
                .body("description", equalTo("Interview Coffee"));
    }

    @Test
    void shouldRejectExpenseWithoutCategoryId() {
        String requestBody = """
                {
                "description": "Invalid Expense",
                "amount": 250.00
                }
                """;

        given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(requestBody)

                .when()
                .post("/api/expenses")

                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Request contains invalid fields"))
                .body("fieldErrors.categoryId", equalTo("must not be null"));
    }

//    Ensure the id int passing is available in the DB to be deleted
    @ParameterizedTest
    @ValueSource(ints = {60})
    void shouldDeleteExpenses(int expenseId) {
        given()
                .baseUri("http://localhost:8080")
                .pathParams("id", expenseId)

                .when()
                .delete("/api/expenses/{id}")

                .then()
                .log().all()
                .statusCode(204);
    }
}