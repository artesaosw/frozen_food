package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

public interface RepositoryFactory {

    CustomerRepo getCustomerRepository();
    DeliveryPackageRepository getDeliveryPackageRepository();
    ProductRepo getProductRepository();
    RouteRepo getRouteRepository();
    SaleOrderRepo getSaleOrderReplicaRepository();
    TransportRepo getTransportRepository();
    
}
