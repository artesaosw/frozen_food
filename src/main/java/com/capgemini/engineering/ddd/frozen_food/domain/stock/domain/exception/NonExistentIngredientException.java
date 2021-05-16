package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.exception;

public class NonExistentIngredientException extends RuntimeException{
    public NonExistentIngredientException(String message) {
        super(message);
    }
}
