package com.tirumareddy.qa.api.model;

import java.math.BigDecimal;

public class ExpenseRequest {

    private String description;
    private BigDecimal amount;
    private Integer categoryId;

    public ExpenseRequest(String description,
                          BigDecimal amount,
                          Integer categoryId) {

        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
