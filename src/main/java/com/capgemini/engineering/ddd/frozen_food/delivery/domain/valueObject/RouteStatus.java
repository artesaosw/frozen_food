package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

public enum RouteStatus {

    /**
     * Route planned and scheduled
     */
    PLANNED_ROUTE,
    /**
     * Route started as scheduled
     */
    STARTED_ROUTE,
    /**
     * Route concluded
     */
    CONCLUDED_ROUTE,
    /**
     * Route rescheduled
     */
    RESCHEDULED_ROUTE,
    /**
     * Route canceled
     */
    CANCELED_ROUTE
}
