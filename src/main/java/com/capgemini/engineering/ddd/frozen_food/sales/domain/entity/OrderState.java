package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

public enum OrderState {
    PROCESSING,
    CONFIRMED,
    DELIVERED,
    RETURNED,
    CANCELLED
}
