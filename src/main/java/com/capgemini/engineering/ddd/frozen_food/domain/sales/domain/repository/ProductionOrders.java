package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductionOrder;

public interface ProductionOrders extends Repository<ProductionOrder, ProductionOrderID> {
}
