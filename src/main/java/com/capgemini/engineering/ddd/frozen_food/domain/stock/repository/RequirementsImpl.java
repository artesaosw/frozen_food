package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Requirement;

import java.util.List;

public class RequirementsImpl implements Requirements {
    @Override
    public List<Requirement> all() {
        // TODO
        return null;
    }

    @Override
    public Requirement withId(RequirementID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(RequirementID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(Requirement aggregateRoot) {
        // TODO
    }

    @Override
    public void update(Requirement aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithName(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean existsMinimumStock(String name) {
        // TODO
        return false;
    }
}
