package com.capgemini.engineering.ddd.frozen_food._shared.event.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.CustomerDTO;
import org.springframework.context.ApplicationEvent;

public class CustomerRegisteredEvent extends ApplicationEvent {

    private CustomerDTO customerDTO;

    public CustomerRegisteredEvent(Object source, CustomerDTO customerDTO) {
        super(source);
        this.customerDTO = customerDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }
}
