package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository.database;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.Product;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository.ProductRepository;

import java.util.List;

public class ProductDatabase implements ProductRepository {

    @Override
    public List<Product> all() {
        return null;
    }

    @Override
    public Product withId(ProductID productID) {
        return null;
    }

    @Override
    public boolean existsWithId(ProductID productID) {
        return false;
    }

    @Override
    public void registerNew(Product product) {
        if(!existsWithId(product.getProductID())){
            //Operation save
        }
    }

    @Override
    public void update(Product product) {
        if(existsWithId(product.getProductID())){
            //update something
        }
    }
}
