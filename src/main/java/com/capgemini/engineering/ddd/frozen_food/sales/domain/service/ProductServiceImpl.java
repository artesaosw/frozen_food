package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_production.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.sales_production.ProductCreatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.sales_production.ProductRemovedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.sales_production.ProductUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.ProductNameAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements DomainServices, ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public Product findProductByName(String name) {
        return this.productRepository.findByName(name).get();
    }

    @Override
    public Product findProductByProductId(ProductID productID) {
        return this.productRepository.findByProductID(productID).get();
    }

    public Product createNewProduct(Product product) {

        if(this.productRepository.existsProductByName(product.getName())) {
            throw new ProductNameAlreadyExistsException("A product with the name " + product.getName() + " already exists.");
        }

        product = this.productRepository.save(product);

        //issue a ProductCreatedEvent
        this.eventPublisher.publishEvent(new ProductCreatedEvent(this,
                product.getProductID()));

        return product;
    }

    @Override
    public Product updateProduct(Product product) {

        //first, validate that id and ProductID of the object passed as argument
        //actually match with an already existing entity in the db
        Product dbProduct = this.productRepository.findById(product.getId()).get();

        if(dbProduct.getId().equals(product.getId())
                && dbProduct.getProductID().isEqualsTo(product.getProductID())) {
            this.productRepository.save(product);

            //issue a ProductUpdatedEvent
            this.eventPublisher.publishEvent(new ProductUpdatedEvent(this,
                    product.getProductID()));
        }

        throw new IllegalArgumentException("The ID or ProductID of the passed object don't match" +
                " with an already existing entity in the DB.");
    }

    //Sales context very likely won't need to delete products by itself.
    @Override
    public void deleteProduct(String id) {

        //Find the product in the db
        Product dbProduct = this.productRepository.findById(id).get();

        //it exists, then delete it
        this.productRepository.deleteById(id);

        //issue a ProductRemovedEvent
        this.eventPublisher.publishEvent(new ProductRemovedEvent(this,
                dbProduct.getProductID()));
    }

    @Override
    public void deleteProductByProductId(ProductID productID) {
        this.productRepository.deleteByProductID(productID);

        //issue a ProductRemovedEvent
        this.eventPublisher.publishEvent(new ProductRemovedEvent(this,productID));
    }

    public ProductRepository getProductRepository() {
        return this.productRepository;
    }

    //TODO: REPLACE THE EVENT IN THE SIGNATURE WITH EVENT
    //THE PRODUCTION CONTEXT ISSUES WHENEVER A NEW PRODUCT IS REGISTERED
    @EventListener
    public void handleCreatedEvent(ApplicationEvent event) {
        // convert entity sent by the production context into a Product (if it's not converted already)
        // then persist it into the database by calling the method
        // this.createNewProduct(Product product)

        //TODO: UNCOMMENT WHEN THE CORRECT EVENT IS ADDED

        //this.productRepository.save(event.getProduct());
    }

    //TODO: REPLACE THE EVENT IN THE SIGNATURE WITH EVENT
    //THE PRODUCTION CONTEXT ISSUES WHENEVER A PRODUCT IS UPDATED
    @EventListener
    public void handleProductUpdatedEvent(ApplicationEvent event) {
        // convert entity sent by the production context into a Product (if it's not converted already)
        // then update it into the database by calling the method
        // this.updateProduct(Product product)
        //Obviously, the ID and ProductID are immutable and can't be changed

        //TODO: UNCOMMENT WHEN THE CORRECT EVENT IS ADDED

//        RecipeDTO recipeDTO = event.getRecipeDTO();
//
//        Product product = this.productRepository.findByProductID(recipeDTO.getProductID()).get();
//        product.setDimensions(recipeDTO.getDimensions());
//        product.setName(recipeDTO.getName());
//        product.setShelfLife(recipeDTO.getShelfLife());
//        product.setAvailable(recipeDTO.isAvailable());
//
//        //persis the updated entity
//        this.productRepository.save(product);
    }

    //TODO: REPLACE THE EVENT IN THE SIGNATURE WITH EVENT
    //THE PRODUCTION CONTEXT ISSUES WHENEVER A PRODUCT IS REMOVED
    @EventListener
    public void handleProductRemovedEvent(ApplicationEvent event) {
        // convert entity sent by the production context into a Product (if it's not converted already)
        // then remove it from database

        //here I'm assuming the event wraps a ProductID we can use to delete the entity

        //TODO: UNCOMMENT WHEN THE CORRECT EVENT IS ADDED
        //this.productRepository.deleteByProductID(event.getProductID());
    }

}
