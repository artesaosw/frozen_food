package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.exception;

public class PostalCodeInvalidException extends IllegalArgumentException{

    public PostalCodeInvalidException(String s) {
        super(s);
    }

}
