package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao.ProductionOrderDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductionOrdersImpl implements ProductionOrders {

    @Autowired
    ProductionOrderDAO productionOrderDAO;

    @Override
    public List<ProductionOrder> all() {
        // TODO
        return null;
    }

    @Override
    public List<ProductionOrder> getAllProductionOrdersByOrderStatus(OrderStatus orderStatus) {
        return productionOrderDAO.getAllProductionOrdersByOrderStatus(orderStatus);
    }

    @Override
    public ProductionOrder withId(ProductionOrderID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(ProductionOrderID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(ProductionOrder aggregateRoot) {
        // TODO
    }

    @Override
    public void update(ProductionOrder aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithOrderReference(String orderReference) {
        // TODO
        return false;
    }
}
