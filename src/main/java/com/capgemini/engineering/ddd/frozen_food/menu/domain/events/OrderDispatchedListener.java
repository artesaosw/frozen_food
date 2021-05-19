package com.capgemini.engineering.ddd.frozen_food.menu.domain.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class OrderDispatchedListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
    }

}
