package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.exception;

public class ProductNameAlreadyExistsException extends RuntimeException {

    public ProductNameAlreadyExistsException(String message) {
        super(message);
    }
}
