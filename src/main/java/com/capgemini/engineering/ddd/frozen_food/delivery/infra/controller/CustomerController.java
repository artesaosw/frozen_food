package com.capgemini.engineering.ddd.frozen_food.delivery.infra.controller;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("delivery/customer")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

}
