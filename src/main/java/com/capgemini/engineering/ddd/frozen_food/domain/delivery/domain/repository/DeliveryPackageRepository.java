package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity.DeliveryPackage;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.DeliveryPackageID;

public interface DeliveryPackageRepository  extends Repository<DeliveryPackage, DeliveryPackageID> {
}
