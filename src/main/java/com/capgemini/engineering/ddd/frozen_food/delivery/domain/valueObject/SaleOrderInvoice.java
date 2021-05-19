package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SaleOrderInvoice {

    @Getter
    private SaleOrderID saleOrderID;

    @Getter @Setter
    private String invoiceDetails;

}
