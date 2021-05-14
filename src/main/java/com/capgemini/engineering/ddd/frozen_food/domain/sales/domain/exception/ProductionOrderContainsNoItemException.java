package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.exception;

public class ProductionOrderContainsNoItemException extends RuntimeException{

    public ProductionOrderContainsNoItemException(String message) {
        super(message);
    }
}
