package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.Stock;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.event.ProductionOrderRegistered;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.event.ProductionOrderUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.repository.ProductionOrders;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.ProductionOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
public class ProductionOrderService {

    @Autowired
    ProductionOrderDAO productionOrderDAO;

    private ProductionOrders productionOrders() {
        return Stock.productionOrders();
    }

    public ProductionOrder getProductionOrderById(@NotNull ProductionOrderID id) {
        if (!productionOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        return productionOrderDAO.findById(id).get();
    }

    public List<ProductionOrder> getAllProductionOrders() {
        return productionOrderDAO.findAll();
    }

    public List<ProductionOrder> getAllProductionOrdersByOrderStatus(@NotNull OrderStatus orderStatus) {
        return productionOrderDAO.findAllByOrderStatus(orderStatus);
    }

    public void registerNewProductionOrder(ProductionOrder productionOrder) {
        if (productionOrderDAO.existsById(productionOrder.id())) {
            throw new DuplicatedEntityException("Already exists another production order with the same id.");
        }
        if (productionOrderDAO.existsByOrderReference(productionOrder.getOrderReference())) {
            throw new DuplicatedEntityException("Already exists another production order with the same order reference.");
        }
        productionOrderDAO.save(productionOrder);
        Events.report(new ProductionOrderRegistered(productionOrder.id()));
    }

    public void registerNewProductionOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        if (productionOrderDAO.existsByOrderReference(orderReference)) {
            throw new DuplicatedEntityException("Already exists another order with the same order reference.");
        }
        ProductionOrder productionOrder = new ProductionOrder(orderReference, orders);
        productionOrderDAO.save(productionOrder);
        Events.report(new ProductionOrderRegistered(productionOrder.id()));
    }

    public void updateProductionOrderStatus(@NotNull ProductionOrderID id, @NotNull OrderStatus orderStatus) {
        if (!productionOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ProductionOrder productionOrder = productionOrderDAO.findById(id).get();
        if (productionOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        productionOrder.setOrderStatus(orderStatus);
        productionOrderDAO.save(productionOrder);
        Events.report(new ProductionOrderUpdated(productionOrder.id()));
    }

    public void updateProductionOrderIngredients(@NotNull ProductionOrderID id, @NotEmpty Map<Ingredient, Integer> orders) {
        if (!productionOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ProductionOrder productionOrder = productionOrderDAO.findById(id).get();
        if (productionOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        productionOrder.setOrders(orders);
        productionOrderDAO.save(productionOrder);
        Events.report(new ProductionOrderUpdated(id));
    }

    public void updateProductionOrder(ProductionOrder productionOrder) {
        if (!productionOrderDAO.existsById(productionOrder.id())) {
            throw new NonExistentEntityException("There is no order with id = " + productionOrder.id());
        }
        productionOrderDAO.save(productionOrder);
        Events.report(new ProductionOrderUpdated(productionOrder.id()));
    }

    public void deleteProductionOrder(ProductionOrderID id) {
        if (!productionOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ProductionOrder productionOrder = productionOrderDAO.findById(id).get();
        productionOrderDAO.delete(productionOrder);
    }
}
