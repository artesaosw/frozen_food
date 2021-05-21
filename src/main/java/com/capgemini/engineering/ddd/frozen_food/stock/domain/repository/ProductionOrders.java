package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

public interface ProductionOrders extends Repository<ProductionOrder, ProductionOrderID> {

}
