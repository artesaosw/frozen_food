package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;

public class ChefOrderUpdate extends DomainEvent {

    private ChefOrderID chefOrderID;

    public ChefOrderUpdate(ChefOrderID chefOrderID) {
        super();
        this.chefOrderID = chefOrderID;
    }
}
