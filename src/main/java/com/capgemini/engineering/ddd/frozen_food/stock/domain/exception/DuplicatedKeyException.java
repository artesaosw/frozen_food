package com.capgemini.engineering.ddd.frozen_food.stock.domain.exception;

public class DuplicatedKeyException extends RuntimeException{
    public DuplicatedKeyException(String message) {
        super(message);
    }
}
