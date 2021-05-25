package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.TransportLimits;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.TransportStatus;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.TransportID;
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

    @Getter @Setter
    private TransportStatus transportStatus;

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
        return this.transportID;
    }

    @Override
    public int hashCode() {
        return AggregateRoot.super.hashcode();
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }
}
