package com.capgemini.engineering.ddd.frozen_food.sales.infra.service;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.sales.infra.repository.CustomerRepository;

import java.util.List;

public interface CustomerService {

    public Customer createNewCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public List<Customer> findAllCustomers();

    public Customer findById(String id);

    public void deleteCustomer(String id);

    public void disableCustomer(String id);

    public void enableCustomer(String id);

    public CustomerRepository getCustomerRepository();


}
