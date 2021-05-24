package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.ProductDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class OrderToOrderDTOConverter {

    /*
    Returns an OrderDTO based on an Order passed as argument.
     */
    public static OrderDTO convertOrderToOrderDTO(Order order) throws CloneNotSupportedException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(Identificator.clone(order.getOrderID()));

        Map<ProductDTO, Integer> productsOrdered = convertProductList(order.getProductsOrdered());

        orderDTO.setOrderDeliveryState(order.getOrderDeliveryState());
        orderDTO.setDeliveryDate(order.getDeliveryDate());

        return orderDTO;
    }

    /*
    Returns a Map<ProductDTO, Integer> based on the Map<Product, Integer> passed as argument.
     */
    private static Map<ProductDTO, Integer> convertProductList(Map<Product, Integer> originalMap) throws CloneNotSupportedException {

        Map<ProductDTO, Integer> productsOrdered = new HashMap<>();

        for(Product product : originalMap.keySet()) {
            ProductDTO productDTO = ProductToProductDTOConverter.ConvertProductToProductDTO(product);
            productsOrdered.put(productDTO, originalMap.get(product));
        }

        return productsOrdered;
    }
}
