package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrder;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderDTOConverter {

    private ProductDTOConverter productDTOConverter;

    public SaleOrder saleOrderDTOtoSaleOrderReplica(SaleOrderDTO saleOrderDTO){
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setSaleOrderID(new SaleOrderID(saleOrderDTO.getSaleOrderID()));
        //orderFromSales.setCustomerID(new CustomerID(saleOrderDTO.getCustomerDTO().getCustomerID()));
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < saleOrderDTO.getProductDTOList().size(); i++){
            productList.add(productDTOConverter.productDTOtoProduct(saleOrderDTO.getProductDTOList().get(i)));
        }
       // saleOrderReplica.setSaleOrderProductReplicas(productReplicaList);
        /*orderFromSales.setSaleOrderDate(saleOrderDTO.getSaleOrderDate());*/
        return saleOrder;
    }
}
