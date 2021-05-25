package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;
;

public interface CustomerRepository extends Repository<Customer, CustomerID> {
}
