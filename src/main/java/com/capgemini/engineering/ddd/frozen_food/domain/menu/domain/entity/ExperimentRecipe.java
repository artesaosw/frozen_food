package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.valueObject.Portion;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExperimentRecipe {

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
