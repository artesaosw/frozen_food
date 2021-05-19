package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Orders;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class OrdersService implements DomainServices {

    private Orders orders() {
        return Domain.orders();
    }

    public void registerNewOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders, @NotNull SupplierID supplierID, @NotNull Integer purchaseValue) {
        if (orders().existsWithOrderReference(orderReference)) {
            throw new IllegalArgumentException("Already exists another order with the same order reference.");
        }
        Order order = new Order(orderReference, orders, supplierID, purchaseValue);
        orders().registerNew(order);
        Events.report(new OrderRegistered(order.id()));
    }

    public void updateOrder(@NotNull OrderID orderID, @NotNull OrderStatus orderStatus) {
        if (!orders().existsWithId(orderID)) {
            throw new IllegalArgumentException("There is no order with id = " + orderID.toString());
        }
        Order order = orders().withId(orderID);
        if (order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        order.setOrderStatus(orderStatus);
        orders().update(order);
        Events.report(new OrderUpdate(orderID));
    }
}
