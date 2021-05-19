package com.capgemini.engineering.ddd.frozen_food.delivery.infra.database;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.ProductReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.ProductRepository;

import java.util.List;

public class ProductDatabase implements ProductRepository {

    @Override
    public List<ProductReplica> all() {
        return null;
    }

    @Override
    public ProductReplica withId(ProductID productID) {
        return null;
    }

    @Override
    public boolean existsWithId(ProductID productID) {
        return false;
    }

    @Override
    public void registerNew(ProductReplica productReplica) {
        if(!existsWithId(productReplica.getProductID())){
            //Operation save
        }
    }

    @Override
    public void update(ProductReplica productReplica) {
        if(existsWithId(productReplica.getProductID())){
            //update something
        }
    }
}
