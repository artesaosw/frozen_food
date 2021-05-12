package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.valueObject.ExperimentRecipe;

public class IngredientsRequested extends DomainEvent {

    private ExperimentRecipe experimentRecipe;

    public IngredientsRequested(ExperimentRecipe experimentRecipe) {
        super();
        this.experimentRecipe = experimentRecipe;
    }
}
