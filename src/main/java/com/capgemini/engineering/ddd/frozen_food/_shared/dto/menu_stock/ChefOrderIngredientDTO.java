package com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChefOrderIngredientDTO {

    private ChefOrderID id;
    // String represents IngredientID
    private Map<String, Integer> orders;

    public ChefOrderIngredientDTO() {
        this.orders = new HashMap<>();
    }
}
