package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class DomainIndependentIDMismatchException extends IllegalArgumentException {

    public DomainIndependentIDMismatchException(String s) {
        super(s);
    }
}
