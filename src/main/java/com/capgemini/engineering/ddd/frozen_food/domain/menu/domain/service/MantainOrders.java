package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.converter.OrderConverter;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.events.StockOrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.events.StockOrderRegisteredPublisher;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.repository.Orders;

import javax.validation.constraints.NotBlank;
import java.util.Map;

public class MantainOrders implements DomainServices {

    private Orders orders() { return Domain.orders();}

    public void registerNew(@NotBlank String orderReference, Map<Ingredient, Integer> articles){

        //Validation
        if (orders().existsWithOrderReference(orderReference)){
            throw new IllegalArgumentException("There is already an order with this reference");
        }

        //Instantiate aggregate
        Order order = new Order(orderReference, articles);

        //persists
        orders().registerNew(order);

        //converts order to orderDTO
        OrderDTO orderDTO = OrderConverter.order2orderDTO(order);

        //reports event
        StockOrderRegisteredPublisher eventPublisher = new
                StockOrderRegisteredPublisher();

        eventPublisher.publishEvent(orderDTO);
    }
}