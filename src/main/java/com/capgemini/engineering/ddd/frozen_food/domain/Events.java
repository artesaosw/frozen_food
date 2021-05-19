package com.capgemini.engineering.ddd.frozen_food.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Events implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T extends DomainEvent> void report(T event){
        //TODO code to implement a com.capgemini.engineering.ddd.frozen_food.domain.producao.event dispatch on Spring
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Events.applicationContext == null){
            Events.applicationContext = applicationContext;
        }
    }
}
