package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe_SalesDTO {

    @JsonProperty(value = "id")
    private RecipeID id;

    @JsonProperty(value = "name")
    private String name;

    public Recipe_SalesDTO() {
    }

    public Recipe_SalesDTO(RecipeID id, String name) {
        this.id = id;
        this.name = name;
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
}
