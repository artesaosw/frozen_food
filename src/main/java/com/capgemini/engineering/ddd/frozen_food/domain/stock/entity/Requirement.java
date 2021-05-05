package com.capgemini.engineering.ddd.frozen_food.domain.stock.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.RequirementStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
public class Requirement implements AggregateRoot, Serializable {

    // TODO o id tem de ser igual ao id do Ingredient do package menu

    private RequirementID id;

    private String name;

    private Unit unit;

    @Setter
    private Integer minimumStockValue;

    @Setter
    private RequirementStatus requirementStatus;

    protected Requirement() {
    }

    public Requirement(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        this.id = Identificator.newInstance(RequirementID.class);
        this.name = name;
        this.unit = unit;
        this.minimumStockValue = minimumStockValue;
        this.requirementStatus = RequirementStatus.INUSE;
    }

    public RequirementID id() {
        return this.id;
    }
}
