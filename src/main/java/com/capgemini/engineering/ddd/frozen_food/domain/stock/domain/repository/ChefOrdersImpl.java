package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao.ChefOrderDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChefOrdersImpl implements ChefOrders {

    @Autowired
    ChefOrderDAO chefOrderDAO;

    @Override
    public List<ChefOrder> all() {
        // TODO
        return null;
    }

    @Override
    public List<ChefOrder> getAllChefOrdersByOrderStatus(OrderStatus orderStatus) {
        return chefOrderDAO.getAllChefOrdersByOrderStatus(orderStatus);
    }

    @Override
    public ChefOrder withId(ChefOrderID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(ChefOrderID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(ChefOrder aggregateRoot) {
        // TODO
    }

    @Override
    public void update(ChefOrder aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithOrderReference(String orderReference) {
        // TODO
        return false;
    }
}
