package com.capgemini.engineering.ddd.frozen_food.menu.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.RecipeID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.valueObject.Portion;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.valueObject.RecipeVersion;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Document(collection = "recipe_menu")
public class Recipe implements AggregateRoot, RecipeItem, Serializable {

    @Id
    private RecipeID id;

    private String name;

    private Map<RecipeItem, Portion> items;

    private String procedure;

    private RecipeVersion version;

    private boolean integratesCatalog;

    //Just to support ORM frameworks
    protected Recipe() {}

    @JsonCreator
    public Recipe(@NotBlank String name, @NotEmpty Set<Portion> items, @NotBlank String procedure, boolean integratesCatalog) {
        this.id = Identificator.newInstance(RecipeID.class);
        this.name = name;
        this.items = mapOf(items);
        this.procedure = procedure;
        this.version = RecipeVersion.initial();
        this.integratesCatalog = integratesCatalog;
    }

    private Map<RecipeItem, Portion> mapOf(Set<Portion> items) {
        return items
                .stream()
                .collect(
                        Collectors.toMap(
                                a -> a.getItem(),
                                Function.identity()));
    }

    private void incrementVersion(boolean majorUpdate) {
        if (majorUpdate){
            version.incrementMajor();
        } else {
            version.incrementMinor();
        }
    }

    public void putOrUpdateItem(@NotNull Portion portion, boolean majorUpdate){
        items.put(portion.getItem(), portion);
        incrementVersion(majorUpdate);
    }

    public void removeItem(@NotNull RecipeItem item, boolean majorUpdate){
        if (!items.containsKey(item)){
            throw new IllegalArgumentException("Item " + item + " is not contained on this recipe.");
        }
        items.remove(item);
        incrementVersion(majorUpdate);
    }

    @Override
    public RecipeID id() {
        return this.id;
    }

    @Override
    public String title() {
        return getName();
    }
}
