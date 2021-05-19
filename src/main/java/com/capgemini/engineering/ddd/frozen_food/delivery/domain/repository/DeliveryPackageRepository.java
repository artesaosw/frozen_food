package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.DeliveryPackage;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.DeliveryPackageID;

public interface DeliveryPackageRepository  extends Repository<DeliveryPackage, DeliveryPackageID> {
}
