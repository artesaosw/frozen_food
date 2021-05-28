package com.capgemini.engineering.ddd.frozen_food.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;

public interface ProductionOrders extends Repository<ProductionOrder, ProductionOrderID> {
}
