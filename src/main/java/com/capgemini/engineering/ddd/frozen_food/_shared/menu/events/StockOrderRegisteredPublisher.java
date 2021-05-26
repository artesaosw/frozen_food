package com.capgemini.engineering.ddd.frozen_food._shared.menu.events;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.menu.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class StockOrderRegisteredPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final ChefOrderDTO chefOrderDTO) {
        StockOrderRegisteredEvent stockOrderRegisteredEvent = new
                StockOrderRegisteredEvent(this, chefOrderDTO);

        applicationEventPublisher.publishEvent(stockOrderRegisteredEvent);
    }
}
