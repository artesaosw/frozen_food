package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrder;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleOrderRepository extends MongoRepository<SaleOrder, SaleOrderID> {

    /*public List<SaleOrder> allByDate(Date saleOrderReceivedDate){
        List<SaleOrder> allSaleOrdersReceived = all();
        List<SaleOrder> saleOrdersReceivedByDate = new ArrayList<>();
        for(SaleOrder order : allSaleOrdersReceived){
           if(order.getSaleOrderReceivedDate().equals(saleOrderReceivedDate)){
                saleOrdersReceivedByDate.add(order);
            }
        }
        return saleOrdersReceivedByDate;
    }*/
}
