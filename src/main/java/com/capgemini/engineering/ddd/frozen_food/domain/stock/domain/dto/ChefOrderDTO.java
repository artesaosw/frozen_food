package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ChefOrderDTO {

    private String orderReference;
    private Map<IngredientDTO, Integer> orders;
}
