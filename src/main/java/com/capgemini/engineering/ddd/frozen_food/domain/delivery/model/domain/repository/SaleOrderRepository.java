package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.SaleOrderID;

public interface SaleOrderRepository extends Repository<SaleOrderReplica, SaleOrderID> {
}
