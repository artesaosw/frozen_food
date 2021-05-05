package com.capgemini.engineering.ddd.frozen_food.domain.stock.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderSupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.OrderSupplierRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.OrderSupplierStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.OrderSupplierUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.OrderSupplier;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Requirement;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.repository.OrdersSuppliers;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class OrdersSuppliersService implements DomainServices {

    private OrdersSuppliers ordersSuppliers() {
        return Domain.ordersSuppliers();
    }

    public void registerNewOrder(@NotEmpty String orderReference, @NotEmpty Map<Requirement, Integer> orders, @NotNull SupplierID supplierID, @NotNull Integer purchaseValue) {
        if (ordersSuppliers().existsWithOrderReference(orderReference)) {
            throw new IllegalArgumentException("Already exists another order with the same order reference.");
        }
        OrderSupplier orderSupplier = new OrderSupplier(orderReference, orders, supplierID, purchaseValue);
        ordersSuppliers().registerNew(orderSupplier);
        Events.report(new OrderSupplierRegistered(orderSupplier.id()));
    }

    public void updateOrder(@NotNull OrderSupplierID orderSupplierID, @NotNull OrderSupplierStatus orderSupplierStatus) {
        if (!ordersSuppliers().existsWithId(orderSupplierID)) {
            throw new IllegalArgumentException("There is no order with id = " + orderSupplierID.toString());
        }
        OrderSupplier orderSupplier = ordersSuppliers().withId(orderSupplierID);
        if (orderSupplier.getOrderSupplierStatus().equals(OrderSupplierStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        orderSupplier.setOrderSupplierStatus(orderSupplierStatus);
        ordersSuppliers().update(orderSupplier);
        Events.report(new OrderSupplierUpdate(orderSupplierID));
    }
}
