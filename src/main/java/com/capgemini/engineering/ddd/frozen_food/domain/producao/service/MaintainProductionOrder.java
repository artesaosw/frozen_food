package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.ProductionOrders;
import org.springframework.beans.factory.annotation.Autowired;

public class MaintainProductionOrder implements DomainServices {

    @Autowired
    ProductionOrders productionOrders;

}
