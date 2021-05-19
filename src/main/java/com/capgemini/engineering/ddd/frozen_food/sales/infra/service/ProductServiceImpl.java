package com.capgemini.engineering.ddd.frozen_food.sales.infra.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.event.ProductCreatedEventPublisher;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.ProductNameAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.sales.infra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class ProductServiceImpl implements DomainServices, ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createNewProduct(@NotNull Product product) {

        if(this.productRepository.existsProductByName(product.getName())) {
            throw new ProductNameAlreadyExistsException("A product with the name " + product.getName() + " already exists.");
        }

        product = this.productRepository.save(product);

        ProductCreatedEventPublisher eventPublisher = new ProductCreatedEventPublisher();
        eventPublisher.publishEvent(product);

        return product;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
