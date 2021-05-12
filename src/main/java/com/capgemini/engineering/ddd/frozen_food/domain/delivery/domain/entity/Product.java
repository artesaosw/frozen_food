package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@AllArgsConstructor
public class Product implements AggregateRoot, Serializable {

    @Id @Getter
    private ProductID productID;

    @Getter @Setter
    private RecipeID recipeID;

    @Getter @Setter @Positive
    private float weight;

    @Getter @Setter @Positive
    private float volume;

    @Getter @Setter @Positive
    private float height;

    @Getter @Setter @Positive
    private float width;

    @Override
    public Identificator id() {
        return null;
    }
}
