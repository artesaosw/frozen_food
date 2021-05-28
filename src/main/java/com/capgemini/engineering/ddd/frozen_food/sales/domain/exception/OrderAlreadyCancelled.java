package com.capgemini.engineering.ddd.frozen_food.sales.domain.exception;

public class OrderAlreadyCancelled extends IllegalArgumentException{

    public OrderAlreadyCancelled(String s) {
        super(s);
    }
}
