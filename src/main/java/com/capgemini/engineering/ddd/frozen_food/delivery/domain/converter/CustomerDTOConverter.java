package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.CustomerDTO;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.Address;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.CustomerReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;

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
