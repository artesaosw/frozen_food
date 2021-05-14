package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.SupplierOrderRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.SupplierOrderUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.SuppliersOrders;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class SuppliersOrdersService implements DomainServices {

    private SuppliersOrders suppliersOrders() {
        return Domain.suppliersOrders();
    }

    public void registerNewOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders, @NotNull SupplierID supplierID, @NotNull Integer purchaseValue) {
        if (suppliersOrders().existsWithOrderReference(orderReference)) {
            throw new IllegalArgumentException("Already exists another order with the same order reference.");
        }
        SupplierOrder supplierOrder = new SupplierOrder(orderReference, orders, supplierID, purchaseValue);
        suppliersOrders().registerNew(supplierOrder);
        Events.report(new SupplierOrderRegistered(supplierOrder.id()));
    }

    public void updateOrderStatus(@NotNull SupplierOrderID supplierOrderID, @NotNull OrderStatus orderStatus) {
        if (!suppliersOrders().existsWithId(supplierOrderID)) {
            throw new IllegalArgumentException("There is no order with id = " + supplierOrderID.toString());
        }
        SupplierOrder supplierOrder = suppliersOrders().withId(supplierOrderID);
        if (supplierOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        supplierOrder.setOrderStatus(orderStatus);
        suppliersOrders().update(supplierOrder);
        Events.report(new SupplierOrderUpdate(supplierOrderID));
    }
}
