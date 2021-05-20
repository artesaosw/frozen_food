package com.capgemini.engineering.ddd.frozen_food.sales.infra.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;

public interface Customers extends Repository<Customer, CustomerID> {

}
