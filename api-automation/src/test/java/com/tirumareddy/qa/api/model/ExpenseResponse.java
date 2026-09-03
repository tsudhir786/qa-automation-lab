package com.tirumareddy.qa.api.model;

import java.math.BigDecimal;

public class ExpenseResponse {

    private Integer id;
    private String description;
    private BigDecimal amount;
    private Integer categoryId;
    private String categoryName;

    public Integer getId() {
        return id;
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

    public String getCategoryName() {
        return categoryName;
    }
}
