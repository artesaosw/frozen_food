package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.Address;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.delivery.external.CustomerDTO;

public class CustomerDTOConverter {

    public Customer customerDTO2Customer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setCustomerID(customerDTO.getCustomerID());
        customer.setName(customerDTO.getName());
        customer.setNif(new NIF(customerDTO.getNif()));
        customer.setEmail(customerDTO.getEmail());
        customer.setCellphoneNumber(customerDTO.getCellphoneNumber());
        customer.setAddress(new Address(customerDTO.getAddressDTO().getStreet(), customerDTO.getAddressDTO().getDoorNumber(),
                customerDTO.getAddressDTO().getPostalCode(), customerDTO.getAddressDTO().getAddressNote()));
        return customer;
    }
}
