package com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions;

public class IllegalStatusException extends RuntimeException{

    public IllegalStatusException(String message) {
        super(message);
    }
}
