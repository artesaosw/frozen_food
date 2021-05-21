package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.CustomerDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import org.springframework.context.ApplicationEvent;

public class CustomerUpdatedEvent extends ApplicationEvent {

    private CustomerDTO customerDTO;

    public CustomerUpdatedEvent(Object source, CustomerDTO customerDTO) {
        super(source);
        this.customerDTO = customerDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }
}
