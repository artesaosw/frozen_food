package com.capgemini.engineering.ddd.frozen_food.production.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.production.valueObject.Portion;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Document(collection= "recipe_producao")
public class Recipe implements AggregateRoot, Serializable {

    @Id
    private RecipeID id;

    @Setter
    private String name;

    @Setter
    private Map<RecipeItem, Portion> items;

    @Setter
    private String procedure;


    //Just to support ORM frameworks
    protected Recipe() {}

    @JsonCreator
    public Recipe(@NotBlank String name, @NotEmpty Set<Portion> items, @NotBlank String procedure) {
        this.id = Identificator.newInstance(RecipeID.class);;
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

    @Override
    public Identificator id() {
        return this.id;
    }
}
