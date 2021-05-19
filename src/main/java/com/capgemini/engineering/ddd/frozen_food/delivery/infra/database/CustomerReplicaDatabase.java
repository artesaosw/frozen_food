package com.capgemini.engineering.ddd.frozen_food.delivery.infra.database;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.CustomerReplicaRepository;

import java.util.List;

public class CustomerReplicaDatabase implements CustomerReplicaRepository {

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
