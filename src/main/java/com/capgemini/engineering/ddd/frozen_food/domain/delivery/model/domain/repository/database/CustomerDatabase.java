package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository.database;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository.CustomerRepository;

import java.util.List;

public class CustomerDatabase implements CustomerRepository {

    @Override
    public List<CustomerReplica> all() {
        return null;
    }

    @Override
    public CustomerReplica withId(CustomerID customerID) {
        return null;
    }

    @Override
    public boolean existsWithId(CustomerID customerID) {
        return false;
    }

    @Override
    public void registerNew(CustomerReplica customerReplica) {
        if(!existsWithId(customerReplica.getCustomerID())){

        }
    }

    @Override
    public void update(CustomerReplica customerReplica) {
        if(existsWithId(customerReplica.getCustomerID())){

        }
    }
}
