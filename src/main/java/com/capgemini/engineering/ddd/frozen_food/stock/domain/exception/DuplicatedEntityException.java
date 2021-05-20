package com.capgemini.engineering.ddd.frozen_food.stock.domain.exception;

public class DuplicatedEntityException extends RuntimeException{
    public DuplicatedEntityException(String message) {
        super(message);
    }
}
