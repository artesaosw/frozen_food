package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food._shared.id.DomainEventID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
public class OrderTrackingInfo {

    @Getter @Setter
    private DomainEventID orderReceivedEventID;

    @Getter @Setter
    private LocalDateTime saleOrderReceivedDate;

    @Getter @Setter
    private DomainEventID orderProductsRequestedEventID;

    @Getter @Setter
    private LocalDateTime  orderProductsRequestedDate;

    @Getter @Setter
    private DomainEventID orderProductsReceivedEventID;

    @Getter @Setter
    private LocalDateTime  orderProductsReceivedDate;

    @Getter @Setter
    private DomainEventID saleOrderPackedEventID;

    @Getter @Setter
    private LocalDateTime  saleOrderPackedDate;

    @Getter @Setter
    private DomainEventID saleOrderShippedEventID;

    @Getter @Setter
    private LocalDateTime  saleOrderShippedDate;

    @Getter @Setter
    private DomainEventID saleOrderDeliveredEventID;

    @Getter @Setter
    private LocalDateTime  saleOrderDeliveredDate;

    @Getter @Setter
    private DomainEventID saleOrderDeliveryFailedEventID;

    @Getter @Setter
    private LocalDateTime  saleOrderDeliveryFailedDate;

    @Getter @Setter
    private DomainEventID saleOrderDeliveryCancelledEventID;

    @Getter @Setter
    private LocalDateTime  saleOrderDeliveryCancelledDate;
}
