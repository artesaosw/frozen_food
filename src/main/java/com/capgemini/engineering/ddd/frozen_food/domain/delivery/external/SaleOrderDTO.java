package com.capgemini.engineering.ddd.frozen_food.domain.delivery.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleOrderDTO {

    private UUID saleOrderID;

    private Date saleOrderDate;

    private CustomerDTO customerDTO;

    private List<ProductDTO> productDTOList;
}
