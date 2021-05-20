package com.capgemini.engineering.ddd.frozen_food._shared.menu.dto;

import com.capgemini.engineering.ddd.frozen_food._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.RecipeItem;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.valueObject.Portion;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecipeDTO {

    @JsonProperty(value = "id")
    private RecipeID id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "items")
    private Map<RecipeItem, Portion> items;

    public RecipeDTO() {
    }

    public RecipeDTO(RecipeID id, String name, Set<Portion> items) {
        this.id = id;
        this.name = name;
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


    public RecipeID getId() {
        return id;
    }

    public void setId(RecipeID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<RecipeItem, Portion> getItems() {
        return items;
    }

    public void setItems(Map<RecipeItem, Portion> items) {
        this.items = items;
    }

}
