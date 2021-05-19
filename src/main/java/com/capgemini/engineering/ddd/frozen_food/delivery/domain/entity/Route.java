package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.RouteID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.TransportID;
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

        public void checkNewOrders(){

        }


        @Override
        public Identificator id() {
                return null;
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
