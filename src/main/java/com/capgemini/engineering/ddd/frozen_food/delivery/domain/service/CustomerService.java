package com.capgemini.engineering.ddd.frozen_food.delivery.domain.service;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.CustomerRepo;
import com.capgemini.engineering.ddd.frozen_food.delivery.notinuse.Delivery;
import com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private CustomerRepo customerRepository(){
        return Delivery.customerRepository();
    }
}
