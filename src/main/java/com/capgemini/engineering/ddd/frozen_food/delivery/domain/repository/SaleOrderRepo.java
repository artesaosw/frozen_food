package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrder;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

import java.util.Date;
import java.util.List;

public interface SaleOrderRepo extends Repository<SaleOrder, SaleOrderID> {

    List<SaleOrder> allByDate(Date saleOrderReceivedDate);
}
