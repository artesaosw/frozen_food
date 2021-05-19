package com.capgemini.engineering.ddd.frozen_food.menu.domain.events;

import com.capgemini.engineering.ddd.frozen_food.menu.domain.DTO.OrderDTO;
import org.springframework.context.ApplicationEvent;

public class StockOrderRegisteredEvent extends ApplicationEvent {

    private OrderDTO orderDTO;

    public StockOrderRegisteredEvent(Object source, OrderDTO orderDTO) {
        super(source);
        this.orderDTO = orderDTO;
    }

    public OrderDTO getOrderDTO(){
        return this.orderDTO;
    }
}
