package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;

import java.io.Serializable;
import java.util.Map;

public class Order implements AggregateRoot, Serializable {

    private OrderID id;

    private String orderReference;

    private Map<Ingredient, Integer> articles;


    public Order(String orderReference, Map<Ingredient, Integer> articles) {
        this.orderReference = orderReference;
        this.articles = articles;
    }


    @Override
    public OrderID id() {
        return this.id;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

    public OrderID getId() {
        return id;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public Map<Ingredient, Integer> getArticles() {
        return articles;
    }
}
