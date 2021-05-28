package com.capgemini.engineering.ddd.frozen_food.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;

public interface Orders extends Repository<Order, OrderID> {
}
