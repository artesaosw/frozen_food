package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class CustomerDoesNotExistException extends IllegalArgumentException{

    public CustomerDoesNotExistException(String s) {
        super(s);
    }
}
