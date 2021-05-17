package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.external.SaleOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.Product;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.SaleOrderReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.SaleOrderID;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderDTOConverter {

    private ProductDTOConverter productDTOConverter;

    public SaleOrderReplica saleOrderDTOtoSaleOrderReplica(SaleOrderDTO saleOrderDTO){
        SaleOrderReplica saleOrderReplica = new SaleOrderReplica();
        saleOrderReplica.setSaleOrderID(new SaleOrderID(saleOrderDTO.getSaleOrderID()));
        saleOrderReplica.setCustomerID(new CustomerID(saleOrderDTO.getCustomerDTO().getCustomerID()));
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < saleOrderDTO.getProductDTOList().size(); i++){
            productList.add(productDTOConverter.productDTOtoProduct(saleOrderDTO.getProductDTOList().get(i)));
        }
        saleOrderReplica.setSaleOrderProducts(productList);
        saleOrderReplica.setSaleOrderDate(saleOrderDTO.getSaleOrderDate());
        return saleOrderReplica;
    }
}
