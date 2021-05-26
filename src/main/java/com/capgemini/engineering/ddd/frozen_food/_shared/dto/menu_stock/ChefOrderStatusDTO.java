package com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChefOrderStatusDTO {

    private ChefOrderID id;
    private OrderStatus orderStatus;
}