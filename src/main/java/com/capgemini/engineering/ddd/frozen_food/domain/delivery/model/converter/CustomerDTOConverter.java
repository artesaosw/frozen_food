package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.external.CustomerDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer.Address;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;

public class CustomerDTOConverter {

    public CustomerReplica customerDTOtoCustomer(CustomerDTO customerDTO){
        CustomerReplica customerReplica = new CustomerReplica();
        customerReplica.setCustomerID(new CustomerID(customerDTO.getCustomerID()));
        customerReplica.setName(customerDTO.getName());
        customerReplica.setNif(new NIF(customerDTO.getNif()));
        customerReplica.setEmail(customerDTO.getEmail());
        customerReplica.setCellphoneNumber(customerDTO.getCellphoneNumber());
        customerReplica.setAddress(new Address(customerDTO.getAddressDTO().getStreet(), customerDTO.getAddressDTO().getDoorNumber(),
                customerDTO.getAddressDTO().getPostalCode(), customerDTO.getAddressDTO().getAddressNote()));
        return customerReplica;
    }
}
