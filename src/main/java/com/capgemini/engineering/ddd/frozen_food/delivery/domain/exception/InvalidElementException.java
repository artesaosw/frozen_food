package com.capgemini.engineering.ddd.frozen_food.delivery.domain.exception;

public class InvalidElementException extends IllegalArgumentException{
    public InvalidElementException(String s) {

        super(s);
    }
}
