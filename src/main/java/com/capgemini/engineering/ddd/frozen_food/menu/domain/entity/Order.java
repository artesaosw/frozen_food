package com.capgemini.engineering.ddd.frozen_food.menu.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

@Getter
@Document(collection = "order_menu")
public class Order implements AggregateRoot, Serializable {

    @Id
    private OrderID id;

    @Setter
    private String orderReference;

    @Setter
    private Map<Ingredient, Integer> articles;


    @JsonCreator
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

}
