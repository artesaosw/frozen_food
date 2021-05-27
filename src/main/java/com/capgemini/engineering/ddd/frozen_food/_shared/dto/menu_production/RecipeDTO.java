package com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_production;

import com.capgemini.engineering.ddd.frozen_food._shared.id.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.RecipeItem;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.valueObject.Portion;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecipeDTO {

    private RecipeID id;

    private String name;

    private Map<RecipeItem, Portion> items;

    private String procedure;


    public RecipeDTO() {
    }

    public RecipeDTO(RecipeID id, String name, Set<Portion> items, String procedure) {
        this.id = id;
        this.name = name;
        this.items = mapOf(items);
        this.procedure = procedure;
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
