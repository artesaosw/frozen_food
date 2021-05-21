package com.capgemini.engineering.ddd.frozen_food.menu.domain.exception;

public class NonExistentEntityException extends Throwable {
    public NonExistentEntityException(String message) {
        super(message);
    }
}
