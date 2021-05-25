package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

public interface RepositoryFactory {

    CustomerRepository getCustomerRepository();
    DeliveryPackageRepository getDeliveryPackageRepository();
    ProductRepository getProductRepository();
    RouteRepository getRouteRepository();
    SaleOrderReplicaRepository getSaleOrderReplicaRepository();
    TransportRepository getTransportRepository();
    
}
