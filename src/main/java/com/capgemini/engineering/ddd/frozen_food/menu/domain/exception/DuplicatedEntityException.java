package com.capgemini.engineering.ddd.frozen_food.menu.domain.exception;

public class DuplicatedEntityException extends Throwable {
    public DuplicatedEntityException(String message) {
        super(message);
    }
}
