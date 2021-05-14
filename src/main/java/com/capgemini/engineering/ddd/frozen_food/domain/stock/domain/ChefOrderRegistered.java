package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;

public class ChefOrderRegistered extends DomainEvent {

    private ChefOrderID chefOrderID;

    public ChefOrderRegistered(ChefOrderID chefOrderID) {
        super();
        this.chefOrderID = chefOrderID;
    }
}
