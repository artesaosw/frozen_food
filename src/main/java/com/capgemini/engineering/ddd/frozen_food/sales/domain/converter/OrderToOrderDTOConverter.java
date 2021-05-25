package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.ProductDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderToOrderDTOConverter {

    /*
    Returns an OrderDTO based on an Order passed as argument.
     */
    public static OrderDTO convertOrderToOrderDTO(Order order) throws CloneNotSupportedException {
        OrderDTO orderDTO = new OrderDTO();

        //clone OrderID (or maybe not)
        //orderDTO.setOrderID(Identificator.clone(order.getOrderID()));
        orderDTO.setOrderID(order.getOrderID());

        List<ProductDTO> productsOrdered = convertProductList(order.getProductsOrdered());
        orderDTO.setProducts(productsOrdered);
        orderDTO.setQuantities(order.getQuantitiesOrdered());

        orderDTO.setOrderDeliveryState(order.getOrderDeliveryState());
        orderDTO.setDeliveryDate(order.getDeliveryDate());

        return orderDTO;
    }

    /*
    Returns a List<ProductDTO> based on the List<Product> passed as argument.
    The string keys will be the product names.
     */
    private static List<ProductDTO> convertProductList(List<Product> originalList) throws CloneNotSupportedException {

        List<ProductDTO> listProductDTO = new ArrayList<>();

        for(Product product : originalList) {
            listProductDTO.add(ProductToProductDTOConverter.ConvertProductToProductDTO(product));
        }

        return listProductDTO;
    }
}
