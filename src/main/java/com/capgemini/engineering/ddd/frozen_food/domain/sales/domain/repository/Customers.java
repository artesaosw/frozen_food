package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;

import javax.validation.constraints.NotBlank;

public interface Customers extends Repository<Customer, CustomerID> {

}
