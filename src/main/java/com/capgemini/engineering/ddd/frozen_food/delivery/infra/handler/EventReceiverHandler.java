package com.capgemini.engineering.ddd.frozen_food.delivery.infra.handler;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter.CustomerDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter.SaleOrderDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.OrderFromSales;
import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderEvent;

public class EventReceiverHandler {

    private CustomerDTOConverter customerDTOConverter;
    private SaleOrderDTOConverter saleOrderDTOConverter;

    public void processSaleOrderReceived(SaleOrderEvent event){
        Customer customer = new Customer();
       // customer = customerDTOConverter.customerDTOtoCustomer(event.getSaleOrderDTO().getCustomerDTO());
        OrderFromSales orderFromSales = new OrderFromSales();
        orderFromSales = saleOrderDTOConverter.saleOrderDTOtoSaleOrderReplica(event.getSaleOrderDTO());
    }
}
