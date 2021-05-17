package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.ProductionOrderRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.ProductionOrderUpdated;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.ProductionOrders;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
public class ProductionOrdersService {

    private ProductionOrders productionOrders() {
        return Domain.productionOrders();
    }

    public ProductionOrder getProductionOrderById(ProductionOrderID id) {
        // TODO
        return null;
    }

    public List<ProductionOrder> getAllProductionOrders() {
        return productionOrders().all();
    }

    public List<ProductionOrder> getAllProductionOrdersByOrderStatus(OrderStatus orderStatus) {
        return productionOrders().getAllProductionOrdersByOrderStatus(orderStatus);
    }

    public void registerNewProductionOrder(ProductionOrder productionOrder) {
        // TODO
    }

    public void registerNewOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        if (productionOrders().existsWithOrderReference(orderReference)) {
            throw new IllegalArgumentException("Already exists another order with the same order reference.");
        }
        ProductionOrder productionOrder = new ProductionOrder(orderReference, orders);
        productionOrders().registerNew(productionOrder);
        Events.report(new ProductionOrderRegistered(productionOrder.id()));
    }

    public void updateOrderStatus(@NotNull ProductionOrderID productionOrderID, @NotNull OrderStatus orderStatus) {
        if (!productionOrders().existsWithId(productionOrderID)) {
            throw new IllegalArgumentException("There is no order with id = " + productionOrderID.toString());
        }
        ProductionOrder productionOrder = productionOrders().withId(productionOrderID);
        if (productionOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        productionOrder.setOrderStatus(orderStatus);
        productionOrders().update(productionOrder);
        Events.report(new ProductionOrderUpdated(productionOrderID));
    }

    public void updateOrderIngredients(@NotNull ProductionOrderID productionOrderID, @NotEmpty Map<Ingredient, Integer> orders) {
        if (!productionOrders().existsWithId(productionOrderID)) {
            throw new IllegalArgumentException("There is no order with id = " + productionOrderID.toString());
        }
        ProductionOrder productionOrder = productionOrders().withId(productionOrderID);
        if (productionOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        productionOrder.setOrders(orders);
        productionOrders().update(productionOrder);
        Events.report(new ProductionOrderUpdated(productionOrderID));
    }

    public void updateProductionOrder(ProductionOrder productionOrder) {
        // TODO
    }

    public void deleteProductionOrder(ProductionOrderID id) {
        // TODO
    }

    public void updateProductionOrderStatus(ProductionOrderID productionOrderID, OrderStatus orderStatus) {
        // TODO
    }
}
