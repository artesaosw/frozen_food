package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.Stock;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.event.SupplierOrderRegistered;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.event.SupplierOrderUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.SupplierOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.repository.SuppliersOrders;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.SupplierOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SupplierOrderService implements DomainServices {

    @Autowired
    SupplierOrderDAO supplierOrderDAO;

    private SuppliersOrders suppliersOrders() {
        return Stock.suppliersOrders();
    }

    public SupplierOrder getSupplierOrderById(SupplierOrderID id) {
        Optional<SupplierOrder> supplierOrder = supplierOrderDAO.findById(id);
        return supplierOrder.get();
    }

    public List<SupplierOrder> getAllSuppliersOrders() {
        return supplierOrderDAO.findAll();
    }

    public List<SupplierOrder> getAllSuppliersOrdersByOrderStatus(OrderStatus orderStatus) {
        return supplierOrderDAO.findAllByOrderStatus(orderStatus);
    }

    public void registerNewSupplierOrder(@NotNull SupplierOrder supplierOrder) {
        if (supplierOrderDAO.existsById(supplierOrder.getId()) || supplierOrderDAO.existsByOrderReference(supplierOrder.getOrderReference())) {
            throw new IllegalArgumentException("Already exists another supplier order with the same id or order reference.");
        }
        supplierOrderDAO.save(supplierOrder);
        Events.report(new SupplierOrderRegistered(supplierOrder.id()));
    }

    public void registerNewSupplierOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders, @NotNull SupplierID id, @NotNull Integer purchaseValue) {
        if (supplierOrderDAO.existsByOrderReference(orderReference)) {
            throw new IllegalArgumentException("Already exists another order with the same order reference.");
        }
        SupplierOrder supplierOrder = new SupplierOrder(orderReference, orders, id, purchaseValue);
        supplierOrderDAO.save(supplierOrder);
        Events.report(new SupplierOrderRegistered(supplierOrder.id()));
    }

    public void updateSupplierOrderStatus(@NotNull SupplierOrderID id, @NotNull OrderStatus orderStatus) {
        if (!supplierOrderDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no order with id = " + id);
        }
        SupplierOrder supplierOrder = supplierOrderDAO.findById(id).get();
        if (supplierOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        supplierOrder.setOrderStatus(orderStatus);
        supplierOrderDAO.save(supplierOrder);
        Events.report(new SupplierOrderUpdated(supplierOrder.id()));
    }

    public void updateSupplierOrder(SupplierOrder supplierOrder) {
        if (!supplierOrderDAO.existsById(supplierOrder.getId())) {
            throw new IllegalArgumentException("There is no supplier order with id = " + supplierOrder.getId().toString());
        }
        supplierOrderDAO.save(supplierOrder);
        Events.report(new SupplierOrderUpdated(supplierOrder.id()));
    }

    public void deleteSupplierOrder(SupplierOrderID id) {
        if (!supplierOrderDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no supplier order with id = " + id);
        }
        Optional<SupplierOrder> supplierOrder = supplierOrderDAO.findById(id);
        supplierOrderDAO.delete(supplierOrder.get());
    }
}
