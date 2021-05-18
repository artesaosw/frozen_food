package com.capgemini.engineering.ddd.frozen_food.stock.domain.dto;

import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDTO {

    private IngredientID id;
    private String name;
}
