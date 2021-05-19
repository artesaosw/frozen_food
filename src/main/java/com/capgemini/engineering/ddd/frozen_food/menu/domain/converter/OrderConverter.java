package com.capgemini.engineering.ddd.frozen_food.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.menu.domain.DTO.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;

public class OrderConverter {

    public static OrderDTO order2orderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderReference(order.getOrderReference());
        orderDTO.setArticles(order.getArticles());
        return orderDTO;
    }
}
