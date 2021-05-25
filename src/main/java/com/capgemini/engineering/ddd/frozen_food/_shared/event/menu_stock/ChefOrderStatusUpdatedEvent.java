package com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import org.springframework.context.ApplicationEvent;

public class ChefOrderStatusUpdatedEvent extends ApplicationEvent {

    private ChefOrderStatusDTO chefOrderStatusDTO;

    public ChefOrderStatusUpdatedEvent(Object source, ChefOrderStatusDTO chefOrderStatusDTO) {
        super(source);
        this.chefOrderStatusDTO = chefOrderStatusDTO;
    }

    public ChefOrderStatusDTO getChefOrderStatusDTO() {
        return chefOrderStatusDTO;
    }
}
