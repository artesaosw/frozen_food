package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;

public class RequirementStockUpdate extends DomainEvent {

    private RequirementID requirementID;

    public RequirementStockUpdate(RequirementID requirementID) {
        super();
        this.requirementID = requirementID;
    }
}
