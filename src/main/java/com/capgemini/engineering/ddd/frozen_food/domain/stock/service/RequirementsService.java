package com.capgemini.engineering.ddd.frozen_food.domain.stock.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.RequirementRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.RequirementUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.RequirementStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Requirement;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.repository.Requirements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequirementsService implements DomainServices {

    private Requirements requirements() {
        return Domain.requirements();
    }

    public void registerNewRequirement(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        if (requirements().existsWithName(name)) {
            throw new IllegalArgumentException("Already exists another requirement with the same name.");
        }
        Requirement requirement = new Requirement(name, unit, minimumStockValue);
        requirements().registerNew(requirement);
        Events.report(new RequirementRegistered(requirement.id()));
    }

    public void updateRequirementUse(@NotNull RequirementID requirementID, @NotNull RequirementStatus requirementStatus) {
        if (!requirements().existsWithId(requirementID)) {
            throw new IllegalArgumentException("There is no requirement with id = " + requirementID.toString());
        }
        Requirement requirement = requirements().withId(requirementID);
        requirement.setRequirementStatus(requirementStatus);
        requirements().update(requirement);
        Events.report(new RequirementUpdate(requirementID));
    }

    public void updateRequirementMinimumStockValue(@NotNull RequirementID requirementID, @NotNull Integer minimumStockValue) {
        if (!requirements().existsWithId(requirementID)) {
            throw new IllegalArgumentException("There is no requirement with id = " + requirementID.toString());
        }
        Requirement requirement = requirements().withId(requirementID);
        requirement.setMinimumStockValue(minimumStockValue);
        requirements().update(requirement);
        Events.report(new RequirementUpdate(requirementID));
    }

    public void updateStockRequirementFromOrder() {
        // TODO
    }

    public void updateStockRequirementFromProduction() {
        // TODO
    }
}
