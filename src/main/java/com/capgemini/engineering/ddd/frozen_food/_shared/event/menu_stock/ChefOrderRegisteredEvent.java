package com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import org.springframework.context.ApplicationEvent;

public class ChefOrderRegisteredEvent extends ApplicationEvent {

    private ChefOrderDTO chefOrderDTO;

    public ChefOrderRegisteredEvent(Object source, ChefOrderDTO chefOrderDTO) {
        super(source);
        this.chefOrderDTO = chefOrderDTO;
    }

    public ChefOrderDTO getChefOrderDTO() {
        return chefOrderDTO;
    }
}
