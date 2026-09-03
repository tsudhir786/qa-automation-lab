package com.tirumareddy.qa.api.base;

import com.tirumareddy.qa.api.client.ExpenseApiClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {

    protected RequestSpecification requestSpec;
    protected ExpenseApiClient expenseApiClient;


    @BeforeEach
    void setUpApi() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType(ContentType.JSON)
                .build();

        expenseApiClient = new ExpenseApiClient(requestSpec);
    }
}
