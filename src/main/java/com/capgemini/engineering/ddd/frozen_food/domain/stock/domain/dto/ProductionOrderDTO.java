package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dto;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ProductionOrderDTO {

    private String orderReference;
    private Map<Ingredient, Integer> orders;
}
