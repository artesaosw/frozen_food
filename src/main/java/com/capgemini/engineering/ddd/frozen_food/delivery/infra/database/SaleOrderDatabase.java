package com.capgemini.engineering.ddd.frozen_food.delivery.infra.database;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

import java.util.List;

//import org.springframework.data.mongodb.repository.MongoRepository;

public class SaleOrderDatabase implements Repository<SaleOrderReplica, SaleOrderID> /*extends MongoRepository<SaleOrderReplica, SaleOrderID>*/ {

    @Override
    public List<SaleOrderReplica> all() {
        return null;
    }

    @Override
    public SaleOrderReplica withId(SaleOrderID saleOrderID) {
        return null;
    }

    @Override
    public boolean existsWithId(SaleOrderID saleOrderID) {
        return false;
    }

    @Override
    public void registerNew(SaleOrderReplica saleOrderReplica) {
        if(!existsWithId(saleOrderReplica.getSaleOrderID())){

        }
    }

    @Override
    public void update(SaleOrderReplica saleOrderReplica) {
        if(existsWithId(saleOrderReplica.getSaleOrderID())){

        }
    }
}
