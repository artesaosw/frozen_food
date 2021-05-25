package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.RouteStatus;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.RouteID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.TransportID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class Route implements AggregateRoot, Serializable {

        @Id
        @Getter
        private RouteID routeID;

        @Getter @Setter
        private TransportID transportID;

        @Getter @Setter
        private LocalDateTime planningRouteDate;

        @Getter @Setter
        private RouteStatus routeStatus;

        @Getter @Setter
        private List<DeliveryPackage> deliveryPackagesList;

        @Getter @Setter
        private LocalDateTime routeScheduledStart;

        @Getter @Setter
        private LocalDateTime routeScheduledEnd;


        @Getter @Setter
        private LocalDateTime routeRealDeliveryDate;



        public void checkNewOrders(){

        }

        public void planRoute(LocalDateTime saleOrdersOfDay){

        }



        @Override
        public Identificator id() {
                return this.routeID;
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
