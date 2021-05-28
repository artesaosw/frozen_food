package com.capgemini.engineering.ddd.frozen_food.delivery.infra.handler;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderEvent;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter.CustomerDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter.SaleOrderDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrder;

public class EventReceiverHandler {

    private CustomerDTOConverter customerDTOConverter;
    private SaleOrderDTOConverter saleOrderDTOConverter;

    public void processSaleOrderReceived(SaleOrderEvent event){
        Customer customer = new Customer();
       // customer = customerDTOConverter.customerDTOtoCustomer(event.getSaleOrderDTO().getCustomerDTO());
        SaleOrder saleOrder = new SaleOrder();
        saleOrder = saleOrderDTOConverter.saleOrderDTOtoSaleOrderReplica(event.getSaleOrderDTO());
    }
}
