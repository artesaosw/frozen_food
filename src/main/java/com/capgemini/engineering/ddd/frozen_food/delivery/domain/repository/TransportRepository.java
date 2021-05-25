package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Transport;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.TransportID;

import java.util.List;

public interface TransportRepository extends Repository<Transport, TransportID> {

    public List<Transport> availableTransport();
}
