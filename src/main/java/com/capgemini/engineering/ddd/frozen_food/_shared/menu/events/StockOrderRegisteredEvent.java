package com.capgemini.engineering.ddd.frozen_food._shared.menu.events;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import org.springframework.context.ApplicationEvent;

public class StockOrderRegisteredEvent extends ApplicationEvent {

    private ChefOrderDTO chefOrderDTO;

    public StockOrderRegisteredEvent(Object source, ChefOrderDTO chefOrderDTO) {
        super(source);
        this.chefOrderDTO= chefOrderDTO;
    }

    public ChefOrderDTO getChefOrderDTO(){
        return this.chefOrderDTO;
    }
}
