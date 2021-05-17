package com.capgemini.engineering.ddd.frozen_food.domain.delivery.view;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.external.SaleOrderEvent;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.converter.CustomerDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.converter.SaleOrderDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.SaleOrderReplica;

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
