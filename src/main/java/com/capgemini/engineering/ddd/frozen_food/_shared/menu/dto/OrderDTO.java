package com.capgemini.engineering.ddd.frozen_food._shared.menu.dto;

import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;

import java.util.Map;

public class OrderDTO {

    String orderReference;

    Map<Ingredient, Integer> articles;



    public OrderDTO() {
    }

    public OrderDTO(String orderReference, Map<Ingredient, Integer> articles) {
        this.orderReference = orderReference;
        this.articles = articles;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public Map<Ingredient, Integer> getArticles() {
        return articles;
    }

    public void setArticles(Map<Ingredient, Integer> articles) {
        this.articles = articles;
    }
}
