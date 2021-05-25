package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.OrderFromSales;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

import java.util.Date;
import java.util.List;

public interface SaleOrderReplicaRepository extends Repository<OrderFromSales, SaleOrderID> {

    List<OrderFromSales> allByDate(Date saleOrderReceivedDate);
}
