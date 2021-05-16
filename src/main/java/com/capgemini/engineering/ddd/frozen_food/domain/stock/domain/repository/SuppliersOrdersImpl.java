package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao.SupplierOrderDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SuppliersOrdersImpl implements SuppliersOrders {

    @Autowired
    SupplierOrderDAO supplierOrderDAO;

    @Override
    public List<SupplierOrder> all() {
        return supplierOrderDAO.getSuppliersOrders();
    }

    @Override
    public List<SupplierOrder> getAllSuppliersOrdersByOrderStatus(OrderStatus orderStatus) {
        return supplierOrderDAO.getAllSuppliersOrdersByOrderStatus(orderStatus);
    }

    @Override
    public SupplierOrder withId(SupplierOrderID id) {
        return supplierOrderDAO.getSupplierOrderById(id);
    }

    @Override
    public boolean existsWithId(SupplierOrderID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(SupplierOrder aggregateRoot) {
        supplierOrderDAO.addSupplierOrder(aggregateRoot);
    }

    @Override
    public void update(SupplierOrder aggregateRoot) {
        supplierOrderDAO.updateSupplierOrder(aggregateRoot);
    }

    @Override
    public boolean existsWithOrderReference(String orderReference) {
        // TODO
        return false;
    }
}
