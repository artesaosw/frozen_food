package com.capgemini.engineering.ddd.frozen_food.delivery.notinuse;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.CustomerRepo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Delivery implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static CustomerRepo customerRepository(){
        return applicationContext.getBean(CustomerRepo.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext givenApplicationContext) throws BeansException {
        if(applicationContext == null){
            Delivery.applicationContext = givenApplicationContext;
        }
    }
}
