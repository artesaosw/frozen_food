package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.DeliveryPackage;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.DeliveryPackageID;

public interface DeliveryPackageRepository  extends Repository<DeliveryPackage, DeliveryPackageID> {
}
