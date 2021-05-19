package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.RecipeID;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class Recipe implements AggregateRoot, RecipeItem, Serializable {

    private RecipeID id;

    private String name;

    private Map<RecipeItem, Portion> items;

    private String procedure;

    private RecipeVersion version;

    //Just to support ORM frameworks
    protected Recipe() {}

    public Recipe(@NotBlank String name, @NotEmpty Set<Portion> items, @NotBlank String procedure) {
        this.id = Identificator.newInstance(RecipeID.class);
        this.name = name;
        this.items = mapOf(items);
        this.procedure = procedure;
        this.version = RecipeVersion.initial();
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
