package com.tirumareddy.qa.api.tests;

import com.tirumareddy.qa.api.base.BaseApiTest;
import com.tirumareddy.qa.api.model.ExpenseRequest;
import com.tirumareddy.qa.api.model.ExpenseResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseApiTest extends BaseApiTest {

    @Test
    void shouldGetExpenses() {

        given()
                .spec(requestSpec)
                .log()
                .all()

                .when()
                .get("/api/expenses")

                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    void shouldCreateAndGetExpense() {

        ExpenseRequest request =
                new ExpenseRequest("Interview Coffee Sept 2026",
                        new BigDecimal("180.00"),
                        1);

        ExpenseResponse createdExpense =
                given()
                        .spec(requestSpec)
                        .body(request)
                        .log()
                        .all()

                        .when()
                        .post("/api/expenses")

                        .then()
                        .log().all()
                        .statusCode(201)
                        .body("description", equalTo("Interview Coffee Sept 2026"))
                        .extract()
                        .as(ExpenseResponse.class);

        ExpenseResponse fetchedExpense =
                given()
                        .spec(requestSpec)
                        .pathParams("id", createdExpense.getId())

                        .when()
                        .get("/api/expenses/{id}")

                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("id", equalTo(createdExpense.getId()))
                        .body("description", equalTo("Interview Coffee Sept 2026"))
                        .extract()
                        .as(ExpenseResponse.class);

        assertEquals(createdExpense.getId(), fetchedExpense.getId());
        assertEquals(
                createdExpense.getDescription(),
                fetchedExpense.getDescription()
        );

        given()
                .spec(requestSpec)
                .pathParams("id", createdExpense.getId())

                .when()
                .delete("/api/expenses/{id}")

                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    void shouldCreateAndGetExpense2() {

        ExpenseRequest request =
                new ExpenseRequest(
                        "Interview Coffee Sept 2026",
                        new BigDecimal("180.00"),
                        1
                );

        ExpenseResponse createdExpense =
                expenseApiClient.createExpense(request);

        ExpenseResponse fetchedExpense =
                expenseApiClient.getExpense(
                        createdExpense.getId()
                );

        assertEquals(
                createdExpense.getId(),
                fetchedExpense.getId()
        );

        assertEquals(
                createdExpense.getDescription(),
                fetchedExpense.getDescription()
        );

        expenseApiClient.deleteExpense(
                createdExpense.getId()
        );
    }

    @Test
    void shouldCreateExpenseUsingDynamicPayload() {
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("description", "Interview Coffee Sept 2026");
        requestBody.put("amount", new BigDecimal("220.00"));
        requestBody.put("categoryId", 1);

        ExpenseResponse createdExpense =
                given()
                        .spec(requestSpec)
                        .body(requestBody)
                        .log().all()

                        .when()
                        .post("/api/expenses")

                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .as(ExpenseResponse.class);

        assertEquals("Interview Coffee Sept 2026",
                createdExpense.getDescription());

    }

    @Test
    void shouldRejectDynamicPayloadWithoutCategoryId() {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("description", "Dynamic Invalid Expense");
        requestBody.put("amount", new BigDecimal("220.00"));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .post("/api/expenses")

                .then()
                .log().all()
                .statusCode(400)
                .body(
                        "fieldErrors.categoryId",
                        equalTo("must not be null")
                );
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