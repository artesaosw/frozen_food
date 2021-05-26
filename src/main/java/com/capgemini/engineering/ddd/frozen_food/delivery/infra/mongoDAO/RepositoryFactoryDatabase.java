package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.*;

public class RepositoryFactoryDatabase implements RepositoryFactory {

    @Override
    public CustomerRepository getCustomerRepository() {
        return null;
    }

    @Override
    public DeliveryPackageRepository getDeliveryPackageRepository(){
        return null;
    }

    @Override
    public ProductRepository getProductRepository() {
        return null;
    }

    @Override
    public RouteRepository getRouteRepository(){
        return null;
    }

    @Override
    public SaleOrderReplicaRepository getSaleOrderReplicaRepository() {
        return null;
    }

    @Override
    public TransportRepository getTransportRepository(){
        return null;
    }
}
