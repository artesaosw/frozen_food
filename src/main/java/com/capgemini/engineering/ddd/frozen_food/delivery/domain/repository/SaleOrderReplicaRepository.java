package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

public interface SaleOrderReplicaRepository extends Repository<SaleOrderReplica, SaleOrderID> {
}
