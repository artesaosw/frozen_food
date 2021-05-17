package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity;

public enum DeliveryStatus {

    /**
     * Sale order received and products being separed for packaging
     */
    PACKAGE_PREPARATION,
    /**
     * Package ready for delivery
     */
    READY_FOR_DELIVERY,
    /**
     * Package in transit for delivery
     */
    IN_TRANSIT,
    /**
     * Package delivered
     */
    DELIVERED,
    /**
     * Package not delivered (customer not at home, etc) - make another attempt for delivery
     */
    FAILED_DELIVERY,
    /**
     * Delivery cancelled
     */
    CANCELED
}
