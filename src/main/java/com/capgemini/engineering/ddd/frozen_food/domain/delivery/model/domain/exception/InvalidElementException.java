package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.exception;

public class InvalidElementException extends IllegalArgumentException{
    public InvalidElementException(String s) {

        super(s);
    }
}
