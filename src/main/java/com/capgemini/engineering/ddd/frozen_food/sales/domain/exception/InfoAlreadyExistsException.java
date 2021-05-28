package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class InfoAlreadyExistsException extends IllegalArgumentException {

    public InfoAlreadyExistsException(String message) {
        super(message);
    }
}
