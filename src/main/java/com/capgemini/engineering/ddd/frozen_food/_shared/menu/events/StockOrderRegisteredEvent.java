package com.capgemini.engineering.ddd.frozen_food._shared.menu.events;

import com.capgemini.engineering.ddd.frozen_food._shared.menu.dto.OrderDTO;
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
