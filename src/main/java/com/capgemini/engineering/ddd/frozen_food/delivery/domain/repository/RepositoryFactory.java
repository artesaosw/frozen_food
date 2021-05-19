package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

public interface RepositoryFactory {

    CustomerReplicaRepository getCustomerRepository();
    ProductReplicaRepository getProductRepository();
    SaleOrderReplicaRepository getSaleOrderReplicaRepository();
    
}
