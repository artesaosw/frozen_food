package com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions;

public class NonExistentEntityException extends Exception {

    public NonExistentEntityException(String message) {
        super(message);
    }
}
