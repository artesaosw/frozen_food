package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class OrderDTO {

    @JsonProperty(value = "reference")
    String orderReference;

    @JsonProperty(value = "articles")
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
