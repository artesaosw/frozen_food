package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;
import lombok.Getter;

@Getter
public class RequirementRegistered extends DomainEvent {

    private RequirementID requirementID;

    public RequirementRegistered(RequirementID requirementID) {
        super();
        this.requirementID = requirementID;
    }
}
