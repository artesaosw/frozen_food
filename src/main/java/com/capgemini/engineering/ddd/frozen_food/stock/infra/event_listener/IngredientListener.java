package com.capgemini.engineering.ddd.frozen_food.stock.infra.event_listener;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.NewIngredientRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredientDTO2Ingredient;

@Service
public class IngredientListener {

    @Autowired
    IngredientDAO ingredientDAO;

    @EventListener
    public void registerNewIngredient(NewIngredientRegisteredEvent newIngredientRegisteredEvent) {
        IngredientDTO ingredientDTO = newIngredientRegisteredEvent.getIngredientDTO();
        Ingredient ingredient = ingredientDTO2Ingredient(ingredientDTO);
        if (ingredientDAO.existsById(ingredient.id())) {
            // TODO Event to report problem to menu domain
            throw new DuplicatedEntityException("Already exists another ingredient with the same id.");
        }
        if (ingredientDAO.existsByName(ingredient.getName())) {
            // TODO Event to report problem to menu domain
            throw new DuplicatedEntityException("Already exists another ingredient with the same name.");
        }
        ingredientDAO.save(ingredient);
    }
}
