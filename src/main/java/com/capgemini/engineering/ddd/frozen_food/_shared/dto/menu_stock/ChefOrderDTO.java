package com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChefOrderDTO {

    private ChefOrderID id;
    private String orderReference;
    // String represents IngredientID
    private Map<String, Integer> orders;

    public ChefOrderDTO() {
        this.orders = new HashMap<>();
    }
}
