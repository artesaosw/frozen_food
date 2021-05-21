package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.CustomerDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;

public class CustomerToCustomerDTOConverter {

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();

        //close customerID into the dto
        customerDTO.setCustomerID(Identificator.clone(customer.getCustomerID()));

        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCellphoneNumber(customer.getCellphoneNumber());
        customerDTO.setAddress(customer.getAddress());

        return customerDTO;
    }

}
