package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;
;

public interface CustomerRepository  extends Repository<CustomerReplica, CustomerID> {
}
