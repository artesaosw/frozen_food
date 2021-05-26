package com.capgemini.engineering.ddd.frozen_food.menu.domain.events;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderIngredientUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderStatusUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderDispatchedListener  {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void updateChefOrderStatus(ChefOrderStatusUpdatedEvent chefOrderStatusUpdatedEvent) {
        ChefOrderStatusDTO chefOrderStatusDTO = chefOrderStatusUpdatedEvent.getChefOrderStatusDTO();
        chefOrderStatusDTO.setOrderStatus(OrderStatus.ON_DELIVERY);

        applicationEventPublisher.publishEvent(new ChefOrderStatusUpdatedEvent(this, chefOrderStatusDTO));
    }

}
