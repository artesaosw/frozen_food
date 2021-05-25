package com.capgemini.engineering.ddd.frozen_food.delivery.external;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import lombok.Getter;

public class SaleOrderEvent extends DomainEvent {

    private String eventData = "Customer sale order placed with success.";

    @Getter
    private SaleOrderDTO saleOrderDTO;

    public SaleOrderEvent(String eventData) {
//        super(eventData);
        this.saleOrderDTO = saleOrderDTO;
    }
}
