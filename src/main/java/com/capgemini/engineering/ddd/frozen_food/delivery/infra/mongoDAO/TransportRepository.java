package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Transport;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.TransportRepo;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.TransportStatus;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.TransportID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportRepository extends MongoRepository<Transport, TransportID> {


}
