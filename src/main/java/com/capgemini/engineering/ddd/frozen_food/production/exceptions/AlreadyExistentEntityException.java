package com.capgemini.engineering.ddd.frozen_food.production.exceptions;

public class AlreadyExistentEntityException extends Throwable {

    public AlreadyExistentEntityException(String message) {
        super(message);
    }
}
