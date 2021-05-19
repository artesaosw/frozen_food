package com.capgemini.engineering.ddd.frozen_food.delivery.infra;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderEvent;
import com.capgemini.engineering.ddd.frozen_food.delivery.infra.converter.CustomerDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.infra.converter.SaleOrderDTOConverter;
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
