package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.RouteID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.DeliveryPackage;

import java.util.List;

public class Route implements AggregateRoot {

        private RouteID routeID;

        private List<DeliveryPackage> lista ;



        @Override
        public Identificator id() {
                return null;
        }
}
