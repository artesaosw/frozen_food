package com.capgemini.engineering.ddd.frozen_food.delivery.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private UUID productID;

    private String weightUnit;

    private float weight;

    private String volumeUnit;

    private float volume;

    private String lengthUnit;

    private float height;

    private float width;
}
