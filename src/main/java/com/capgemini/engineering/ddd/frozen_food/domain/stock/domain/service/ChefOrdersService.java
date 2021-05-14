package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.ChefOrderRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.ChefOrderUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.ChefOrders;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class ChefOrdersService {

    private ChefOrders chefOrders() {
        return Domain.chefOrders();
    }

    public void registerNewOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        if (chefOrders().existsWithOrderReference(orderReference)) {
            throw new IllegalArgumentException("Already exists another order with the same order reference.");
        }
        ChefOrder chefOrder = new ChefOrder(orderReference, orders);
        chefOrders().registerNew(chefOrder);
        Events.report(new ChefOrderRegistered(chefOrder.id()));
    }

    public void updateOrderStatus(@NotNull ChefOrderID chefOrderID, @NotNull OrderStatus orderStatus) {
        if (!chefOrders().existsWithId(chefOrderID)) {
            throw new IllegalArgumentException("There is no order with id = " + chefOrderID.toString());
        }
        ChefOrder chefOrder = chefOrders().withId(chefOrderID);
        if (chefOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        chefOrder.setOrderStatus(orderStatus);
        chefOrders().update(chefOrder);
        Events.report(new ChefOrderUpdate(chefOrderID));
    }

    public void updateOrderIngredients(@NotNull ChefOrderID chefOrderID, @NotEmpty Map<Ingredient, Integer> orders) {
        if (!chefOrders().existsWithId(chefOrderID)) {
            throw new IllegalArgumentException("There is no order with id = " + chefOrderID.toString());
        }
        ChefOrder chefOrder = chefOrders().withId(chefOrderID);
        if (chefOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        chefOrder.setOrders(orders);
        chefOrders().update(chefOrder);
        Events.report(new ChefOrderUpdate(chefOrderID));
    }
}
