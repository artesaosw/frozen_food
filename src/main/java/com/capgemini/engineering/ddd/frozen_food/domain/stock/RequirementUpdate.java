package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;

public class RequirementUpdate extends DomainEvent {

    private RequirementID requirementID;

    public RequirementUpdate(RequirementID requirementID) {
        super();
        this.requirementID = requirementID;
    }
}
