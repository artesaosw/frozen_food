package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.OrderFromSales;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.SaleOrderReplicaRepository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.data.mongodb.repository.MongoRepository;

public class SaleOrderDatabase implements SaleOrderReplicaRepository /*extends MongoRepository<SaleOrderReplica, SaleOrderID>*/ {

    @Override
    public List<OrderFromSales> all() {
        return null;
    }

    @Override
    public OrderFromSales withId(SaleOrderID saleOrderID) {
        return null;
    }

    @Override
    public boolean existsWithId(SaleOrderID saleOrderID) {
        return false;
    }

    @Override
    public void registerNew(OrderFromSales orderFromSales) {
        if(!existsWithId(orderFromSales.getSaleOrderID())){

        }
    }

    @Override
    public void update(OrderFromSales orderFromSales) {
        if(existsWithId(orderFromSales.getSaleOrderID())){

        }
    }

    @Override
    public List<OrderFromSales> allByDate(Date saleOrderReceivedDate){
        List<OrderFromSales> allSaleOrdersReceived = all();
        List<OrderFromSales> saleOrdersReceivedByDate = new ArrayList<>();
        for(OrderFromSales order : allSaleOrdersReceived){
           /* if(order.getSaleOrderReceivedDate().equals(saleOrderReceivedDate)){
                saleOrdersReceivedByDate.add(order);
            }*/
        }
        return saleOrdersReceivedByDate;
    }
}
