package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.DeliveryOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.ProductDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderToOrderDTOConverter {

    /*
    Returns an OrderDTO based on an Order passed as argument.
     */
    public static DeliveryOrderDTO convertOrderToOrderDTO(Order order) throws CloneNotSupportedException {
        DeliveryOrderDTO deliveryOrderDTO = new DeliveryOrderDTO();

        //clone OrderID (or maybe not)
        //orderDTO.setOrderID(Identificator.clone(order.getOrderID()));
        deliveryOrderDTO.setOrderID(order.getOrderID());

        List<ProductDTO> productsOrdered = convertProductList(order);
        deliveryOrderDTO.setProducts(productsOrdered);

        deliveryOrderDTO.setOrderedBy(CustomerToCustomerDTOConverter.convertCustomerToCustomerDTO(order.getOrderedBy()));

        deliveryOrderDTO.setOrderStatus(order.getOrderStatus());
        deliveryOrderDTO.setDeliveryDate(order.getDeliveryDate());

        return deliveryOrderDTO;
    }

    /*
    Returns a List<ProductDTO> based on the Order object passed as argument.
     */
    private static List<ProductDTO> convertProductList(Order order) throws CloneNotSupportedException {

        List<ProductDTO> listProductDTO = new ArrayList<>();

        for (int i = 0; i < order.getProductsOrdered().size(); i++) {
            listProductDTO.add(
                    ProductToProductDTOConverter.ConvertProductToProductDTO(order.getProductsOrdered().get(i),
                            order.getQuantitiesOrdered().get(i)));
        }
        return listProductDTO;
    }
}
