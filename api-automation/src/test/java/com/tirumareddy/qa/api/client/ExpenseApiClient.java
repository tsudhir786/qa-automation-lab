package com.tirumareddy.qa.api.client;

import com.tirumareddy.qa.api.model.ExpenseRequest;
import com.tirumareddy.qa.api.model.ExpenseResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ExpenseApiClient {

    private final RequestSpecification requestSpec;

    public ExpenseApiClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {

        return given()
                .spec(requestSpec)
                .body(request)

                .when()
                .post("/api/expenses")

                .then()
                .statusCode(201)
                .extract()
                .as(ExpenseResponse.class);
    }

    public ExpenseResponse getExpense(Integer id) {

        return given()
                .spec(requestSpec)
                .pathParam("id", id)

                .when()
                .get("/api/expenses/{id}")

                .then()
                .statusCode(200)
                .extract()
                .as(ExpenseResponse.class);
    }

    public void deleteExpense(Integer id) {

        given()
                .spec(requestSpec)
                .pathParam("id", id)

                .when()
                .delete("/api/expenses/{id}")

                .then()
                .statusCode(204);
    }
}
