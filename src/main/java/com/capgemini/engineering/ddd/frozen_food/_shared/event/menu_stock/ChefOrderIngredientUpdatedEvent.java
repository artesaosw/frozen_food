package com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderIngredientDTO;
import org.springframework.context.ApplicationEvent;

public class ChefOrderIngredientUpdatedEvent extends ApplicationEvent {

    private ChefOrderIngredientDTO chefOrderIngredientDTO;

    public ChefOrderIngredientUpdatedEvent(Object source, ChefOrderIngredientDTO chefOrderIngredientDTO) {
        super(source);
        this.chefOrderIngredientDTO = chefOrderIngredientDTO;
    }

    public ChefOrderIngredientDTO getChefOrderIngredientDTO() {
        return chefOrderIngredientDTO;
    }
}
