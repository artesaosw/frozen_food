package com.capgemini.engineering.ddd.frozen_food.menu.domain.events;

import com.capgemini.engineering.ddd.frozen_food.menu.domain.DTO.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class StockOrderRegisteredPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final OrderDTO orderDTO) {
        StockOrderRegisteredEvent stockOrderRegisteredEvent = new
                StockOrderRegisteredEvent(this, orderDTO);

        applicationEventPublisher.publishEvent(stockOrderRegisteredEvent);
    }
}
