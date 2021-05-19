package com.capgemini.engineering.ddd.frozen_food.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.valueObject.ExperimentRecipe;

public class IngredientsRequested extends DomainEvent {

    private ExperimentRecipe experimentRecipe;

    public IngredientsRequested(ExperimentRecipe experimentRecipe) {
        super();
        this.experimentRecipe = experimentRecipe;
    }
}
