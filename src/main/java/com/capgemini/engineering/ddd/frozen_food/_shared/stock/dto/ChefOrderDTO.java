package com.capgemini.engineering.ddd.frozen_food._shared.stock.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChefOrderDTO {

    private String orderReference;
    private Map<IngredientDTO, Integer> orders;

    public ChefOrderDTO() {
        this.orders = new HashMap<>();
    }
}
