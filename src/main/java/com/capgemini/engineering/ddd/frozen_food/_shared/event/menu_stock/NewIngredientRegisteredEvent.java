package com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import org.springframework.context.ApplicationEvent;

public class NewIngredientRegisteredEvent extends ApplicationEvent {

    private IngredientDTO ingredientDTO;

    public NewIngredientRegisteredEvent(Object source, IngredientDTO ingredientDTO) {
        super(source);
        this.ingredientDTO = ingredientDTO;
    }

    public IngredientDTO getIngredientDTO() {
        return ingredientDTO;
    }
}
