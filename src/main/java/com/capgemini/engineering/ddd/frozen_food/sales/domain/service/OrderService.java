package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.OrderRepository;

import java.util.List;

public interface OrderService {

    public Order registerNewOrder(Order order) throws CloneNotSupportedException;

    public void cancelOrder(String id) throws CloneNotSupportedException;

    public void updateOrder(Order order);

    public List<Order> getAllOrders();

    public Order getOrderById(String id);

    public OrderRepository getOrderRepository();
}
