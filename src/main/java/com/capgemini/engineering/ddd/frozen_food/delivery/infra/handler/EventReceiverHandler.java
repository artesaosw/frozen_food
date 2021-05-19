package com.capgemini.engineering.ddd.frozen_food.delivery.infra.handler;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderEvent;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter.CustomerDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter.SaleOrderDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrderReplica;

public class EventReceiverHandler {

    private CustomerDTOConverter customerDTOConverter;
    private SaleOrderDTOConverter saleOrderDTOConverter;

    public void processSaleOrderReceived(SaleOrderEvent event){
        CustomerReplica customerReplica = new CustomerReplica();
        customerReplica = customerDTOConverter.customerDTOtoCustomer(event.getSaleOrderDTO().getCustomerDTO());
        SaleOrderReplica saleOrderReplica = new SaleOrderReplica();
        saleOrderReplica = saleOrderDTOConverter.saleOrderDTOtoSaleOrderReplica(event.getSaleOrderDTO());
    }
}
