package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.OrderFromSales;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;
import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderDTOConverter {

    private ProductDTOConverter productDTOConverter;

    public OrderFromSales saleOrderDTOtoSaleOrderReplica(SaleOrderDTO saleOrderDTO){
        OrderFromSales orderFromSales = new OrderFromSales();
        orderFromSales.setSaleOrderID(new SaleOrderID(saleOrderDTO.getSaleOrderID()));
        //orderFromSales.setCustomerID(new CustomerID(saleOrderDTO.getCustomerDTO().getCustomerID()));
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < saleOrderDTO.getProductDTOList().size(); i++){
            productList.add(productDTOConverter.productDTOtoProduct(saleOrderDTO.getProductDTOList().get(i)));
        }
       // saleOrderReplica.setSaleOrderProductReplicas(productReplicaList);
        /*orderFromSales.setSaleOrderDate(saleOrderDTO.getSaleOrderDate());*/
        return orderFromSales;
    }
}
