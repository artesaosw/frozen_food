package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class OrderAlreadyExistsException extends RuntimeException{

    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
