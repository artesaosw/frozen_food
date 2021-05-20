package com.capgemini.engineering.ddd.frozen_food._shared.stock.dto;

import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientDTO {

    private IngredientID id;
    private String name;
    private Unit unit;
}
