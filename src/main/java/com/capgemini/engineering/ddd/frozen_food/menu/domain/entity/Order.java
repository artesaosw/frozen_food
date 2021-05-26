package com.capgemini.engineering.ddd.frozen_food.menu.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
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
    private ChefOrderID id;

    @Setter
    private String orderReference;

    @Setter
    private Map<String, Integer> orders;


    @JsonCreator
    public Order(String orderReference, Map<String, Integer> orders) {
        this.orderReference = orderReference;
        this.orders = orders;
    }

    @Override
    public ChefOrderID id() {
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
