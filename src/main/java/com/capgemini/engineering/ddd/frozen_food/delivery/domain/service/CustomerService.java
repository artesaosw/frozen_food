package com.capgemini.engineering.ddd.frozen_food.delivery.domain.service;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO.CustomerDatabase;
import com.capgemini.engineering.ddd.frozen_food.delivery.notinuse.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerDatabase customerDatabase;

    private CustomerRepository customerRepository(){
        return Delivery.customerRepository();
    }
}
