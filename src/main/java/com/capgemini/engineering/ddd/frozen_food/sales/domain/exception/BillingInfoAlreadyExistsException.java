package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class BillingInfoAlreadyExistsException extends IllegalArgumentException {

    public BillingInfoAlreadyExistsException(String message) {
        super(message);
    }
}
