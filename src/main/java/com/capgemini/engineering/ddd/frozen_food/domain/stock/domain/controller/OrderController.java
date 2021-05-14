package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.controller;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service.ChefOrdersService;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service.ProductionOrdersService;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ChefOrdersService chefOrdersService;
    @Autowired
    private ProductionOrdersService productionOrdersService;
    @Autowired
    private SuppliersService suppliersService;

    public OrderController() {
    }


}
