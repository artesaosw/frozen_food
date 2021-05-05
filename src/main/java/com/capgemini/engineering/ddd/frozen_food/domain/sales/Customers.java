package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;

import javax.validation.constraints.NotBlank;

public interface Customers extends Repository<Customer, CustomerID> {

    boolean existsWithName(@NotBlank String name);
}
