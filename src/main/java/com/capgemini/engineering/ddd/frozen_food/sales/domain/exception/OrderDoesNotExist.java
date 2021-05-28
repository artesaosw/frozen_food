package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class OrderDoesNotExist extends IllegalArgumentException{

    public OrderDoesNotExist(String s) {
        super(s);
    }
}
