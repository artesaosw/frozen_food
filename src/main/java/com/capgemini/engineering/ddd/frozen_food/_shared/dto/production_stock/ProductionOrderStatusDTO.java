package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductionOrderStatusDTO {

    private ProductionOrderID id;
    private OrderStatus orderStatus;
}
