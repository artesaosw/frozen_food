package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.saleOrder;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Date;

@AllArgsConstructor
public class SaleOrder implements AggregateRoot {

    @Id @Getter
    private SaleOrderID saleOrderID;

    @Getter
    private CustomerID customerID;

    @Getter
    private List<Product> saleOrderProducts;

    @Getter
    private Date saleOrderDate;


    @Override
    public Identificator id() {
        return null;
    }
}
