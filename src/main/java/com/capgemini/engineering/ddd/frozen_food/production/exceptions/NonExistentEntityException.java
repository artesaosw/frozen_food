package com.capgemini.engineering.ddd.frozen_food.production.exceptions;

public class NonExistentEntityException extends Exception {

    public NonExistentEntityException(String message) {
        super(message);
    }
}
