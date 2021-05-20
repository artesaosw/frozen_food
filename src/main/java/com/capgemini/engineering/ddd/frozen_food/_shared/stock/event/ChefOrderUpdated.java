package com.capgemini.engineering.ddd.frozen_food._shared.stock.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;

public class ChefOrderUpdated extends DomainEvent {

    private ChefOrderID chefOrderID;

    public ChefOrderUpdated(ChefOrderID chefOrderID) {
        super();
        this.chefOrderID = chefOrderID;
    }
}
