package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.SaleOrderID;

public interface SaleOrderRepository extends Repository<SaleOrderReplica, SaleOrderID> {
}
