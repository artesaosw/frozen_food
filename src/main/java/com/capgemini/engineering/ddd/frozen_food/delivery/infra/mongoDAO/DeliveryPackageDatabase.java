package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.DeliveryPackage;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.DeliveryPackageRepository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.DeliveryPackageID;

import java.util.List;

public class DeliveryPackageDatabase implements DeliveryPackageRepository {

    @Override
    public List<DeliveryPackage> all() {
        return null;
    }

    @Override
    public DeliveryPackage withId(DeliveryPackageID id) {
        return null;
    }

    @Override
    public boolean existsWithId(DeliveryPackageID id) {
        return false;
    }

    @Override
    public void registerNew(DeliveryPackage aggregateRoot) {

    }

    @Override
    public void update(DeliveryPackage aggregateRoot) {

    }
}
