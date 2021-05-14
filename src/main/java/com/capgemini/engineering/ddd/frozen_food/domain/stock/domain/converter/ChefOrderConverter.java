package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dto.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ChefOrder;

import java.util.ArrayList;
import java.util.List;

public class ChefOrderConverter {

    public static ChefOrderDTO chefOrder2ChefOrderDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderDTO chefOrderDTO = new ChefOrderDTO();
        chefOrderDTO.setOrderReference(chefOrder.getOrderReference());
        chefOrderDTO.setOrders(chefOrder.getOrders());
        return chefOrderDTO;
    }

    public static ChefOrder chefOrderDTO2ChefOrder(ChefOrderDTO chefOrderDTO) throws NullPointerException {
        ChefOrder chefOrder = new ChefOrder();
        chefOrder.setOrderReference(chefOrderDTO.getOrderReference());
        chefOrder.setOrders(chefOrderDTO.getOrders());
        return chefOrder;
    }

    public static List<ChefOrderDTO> chefOrderList2ChefOrderListDTO(List<ChefOrder> chefOrderList) throws NullPointerException {
        List<ChefOrderDTO> chefOrderDTOList = new ArrayList<>();
        for (ChefOrder chefOrder : chefOrderList) {
            ChefOrderDTO chefOrderDTO = chefOrder2ChefOrderDTO(chefOrder);
            chefOrderDTOList.add(chefOrderDTO);
        }
        return chefOrderDTOList;
    }

    public static List<ChefOrder> chefOrderListDTO2ChefOrderList(List<ChefOrderDTO> chefOrderDTOList) throws NullPointerException {
        List<ChefOrder> chefOrderList = new ArrayList<>();
        for (ChefOrderDTO chefOrderDTO : chefOrderDTOList) {
            ChefOrder chefOrder = chefOrderDTO2ChefOrder(chefOrderDTO);
            chefOrderList.add(chefOrder);
        }
        return chefOrderList;
    }
}
