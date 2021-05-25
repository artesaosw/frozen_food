package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

public enum OrderStatus {

    /**
     * Sale Order received from Sales
     */
    ORDER_RECEIVED_FROM_SALES,
    /**
     * Order planned, with an assigned route and products of the sale order requested to Production
     */
    ORDER_PRODUCTS_REQUESTED_TO_PRODUCTION,
    /**
     * Sale order products received from Production and products being separated for packaging
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
    CANCELLED
}
