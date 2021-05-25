package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.event.SupplierOrderRegistered;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.event.SupplierOrderUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.SupplierOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.SupplierOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
public class SupplierOrderService implements DomainServices {

    @Autowired
    SupplierOrderDAO supplierOrderDAO;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public SupplierOrder getSupplierOrderById(SupplierOrderID id) {
        if (!supplierOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        return supplierOrderDAO.findById(id).get();
    }

    public List<SupplierOrder> getAllSuppliersOrders() {
        return supplierOrderDAO.findAll();
    }

    public List<SupplierOrder> getAllSuppliersOrdersByOrderStatus(OrderStatus orderStatus) {
        return supplierOrderDAO.findAllByOrderStatus(orderStatus);
    }

    public void registerNewSupplierOrder(@NotNull SupplierOrder supplierOrder) {
        if (supplierOrderDAO.existsById(supplierOrder.id())) {
            throw new DuplicatedEntityException("Already exists another supplier order with the same id.");
        }
        if (supplierOrderDAO.existsByOrderReference(supplierOrder.getOrderReference())) {
            throw new DuplicatedEntityException("Already exists another supplier order with the same order reference.");
        }
        supplierOrderDAO.save(supplierOrder);
    }

    public void registerNewSupplierOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders, @NotNull SupplierID id, @NotNull Integer purchaseValue) {
        if (supplierOrderDAO.existsByOrderReference(orderReference)) {
            throw new DuplicatedEntityException("Already exists another order with the same order reference.");
        }
        SupplierOrder supplierOrder = new SupplierOrder(orderReference, orders, id, purchaseValue);
        supplierOrderDAO.save(supplierOrder);
    }

    public void updateSupplierOrderStatus(@NotNull SupplierOrderID id, @NotNull OrderStatus orderStatus) {
        if (!supplierOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        SupplierOrder supplierOrder = supplierOrderDAO.findById(id).get();
        if (supplierOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        supplierOrder.setOrderStatus(orderStatus);
        supplierOrderDAO.save(supplierOrder);
    }

    public void updateSupplierOrder(SupplierOrder supplierOrder) {
        if (!supplierOrderDAO.existsById(supplierOrder.id())) {
            throw new NonExistentEntityException("There is no supplier order with id = " + supplierOrder.id());
        }
        supplierOrderDAO.save(supplierOrder);
    }

    public void deleteSupplierOrder(SupplierOrderID id) {
        if (!supplierOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no supplier order with id = " + id);
        }
        SupplierOrder supplierOrder = supplierOrderDAO.findById(id).get();
        supplierOrderDAO.delete(supplierOrder);
    }
}
