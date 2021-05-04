package com.capgemini.engineering.ddd.frozen_food.domain.stock.exception;

public class InvalidElementException extends RuntimeException{
    public InvalidElementException(String message) {
        super(message);
    }
}
