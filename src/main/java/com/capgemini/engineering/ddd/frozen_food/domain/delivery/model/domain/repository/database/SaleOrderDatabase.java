package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository.database;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.SaleOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository.SaleOrderRepository;

import java.util.List;

public class SaleOrderDatabase implements SaleOrderRepository {

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
