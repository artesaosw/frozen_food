package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.TransportID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Transport implements AggregateRoot, Serializable {

    @Id @Getter
    private TransportID transportID;

    @Getter @Setter
    private String transportDescription;

    @Getter @Setter
    private TransportLimits transportLimits;

    @Getter
    private float weightTransported;

    @Getter
    private float volumeTransported;

    @Getter
    private float heightTransported;

    @Getter
    private float widthTransported;




    @Override
    public Identificator id() {
        return null;
    }
}
