package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.exception;

public class BillingInfoAlreadyExistsException extends RuntimeException {

    public BillingInfoAlreadyExistsException(String message) {
        super(message);
    }
}
