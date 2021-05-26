package com.capgemini.engineering.ddd.frozen_food.menu.domain.events;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderStatusUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.menu.infra.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderDispatchedListener  {

    @Autowired
    OrderDAO orderDAO;

    @EventListener
    public void updateChefOrderStatus(ChefOrderStatusUpdatedEvent chefOrderStatusUpdatedEvent) {
        ChefOrderStatusDTO chefOrderStatusDTO = chefOrderStatusUpdatedEvent.getChefOrderStatusDTO();
        Order order = orderDAO.findById(chefOrderStatusDTO.getId()).get();
        order.setOrderStatus(chefOrderStatusDTO.getOrderStatus());
        orderDAO.save(order);
    }

}
