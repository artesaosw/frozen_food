package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.exception;

public class RecipeVersionOverflowException extends RuntimeException{
    public RecipeVersionOverflowException(String message) {
        super(message);
    }
}
