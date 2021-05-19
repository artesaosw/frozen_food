package com.capgemini.engineering.ddd.frozen_food.delivery.infra.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.SaleOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.ProductReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.SaleOrderID;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderDTOConverter {

    private ProductDTOConverter productDTOConverter;

    public SaleOrderReplica saleOrderDTOtoSaleOrderReplica(SaleOrderDTO saleOrderDTO){
        SaleOrderReplica saleOrderReplica = new SaleOrderReplica();
        saleOrderReplica.setSaleOrderID(new SaleOrderID(saleOrderDTO.getSaleOrderID()));
        saleOrderReplica.setCustomerID(new CustomerID(saleOrderDTO.getCustomerDTO().getCustomerID()));
        List<ProductReplica> productReplicaList = new ArrayList<>();
        for(int i = 0; i < saleOrderDTO.getProductDTOList().size(); i++){
            productReplicaList.add(productDTOConverter.productDTOtoProduct(saleOrderDTO.getProductDTOList().get(i)));
        }
        saleOrderReplica.setSaleOrderProductReplicas(productReplicaList);
        saleOrderReplica.setSaleOrderDate(saleOrderDTO.getSaleOrderDate());
        return saleOrderReplica;
    }
}
