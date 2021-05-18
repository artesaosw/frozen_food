package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.ValueObject;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.RecipeItem;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class ExperimentRecipe implements ValueObject, Serializable {

    private Map<RecipeItem, Portion> items;

    public ExperimentRecipe(Set<Portion> items) {
        this.items = mapOf(items);
    }


    private Map<RecipeItem, Portion> mapOf(Set<Portion> items) {
        return items
                .stream()
                .collect(
                        Collectors.toMap(
                                a -> a.getItem(),
                                Function.identity()));
    }
}
