package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>, Customers {

    public boolean existsCustomerByBillingInfo(String billingInfo);

}
