package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.exception;

public class EmailInvalidException extends IllegalArgumentException{
    public EmailInvalidException(String s) {

        super(s);
    }
}
