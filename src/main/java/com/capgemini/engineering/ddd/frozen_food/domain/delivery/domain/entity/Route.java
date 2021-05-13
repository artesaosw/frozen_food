package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.RouteID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.TransportID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class Route implements AggregateRoot, Serializable {

        @Id @Getter
        private RouteID routeID;

        @Getter @Setter
        private TransportID transportID;

        @Getter @Setter
        private Date planningRouteDate;

        @Getter @Setter
        private RouteStatus routeStatus;

        @Getter @Setter
        private List<DeliveryPackage> deliveryPackagesList;

        @Getter @Setter
        private Date deliveryScheduledDate;

        @Override
        public Identificator id() {
                return null;
        }
}
