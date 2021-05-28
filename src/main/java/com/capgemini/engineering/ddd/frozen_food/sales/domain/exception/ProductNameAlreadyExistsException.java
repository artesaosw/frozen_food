package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class ProductNameAlreadyExistsException extends IllegalArgumentException {

    public ProductNameAlreadyExistsException(String message) {
        super(message);
    }
}
