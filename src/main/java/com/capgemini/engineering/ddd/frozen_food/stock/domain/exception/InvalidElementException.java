package com.capgemini.engineering.ddd.frozen_food.stock.domain.exception;

public class InvalidElementException extends RuntimeException {
    public InvalidElementException(String message) {
        super(message);
    }
}
