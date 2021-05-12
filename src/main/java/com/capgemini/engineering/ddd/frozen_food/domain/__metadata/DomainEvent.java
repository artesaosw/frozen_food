package com.capgemini.engineering.ddd.frozen_food.domain.__metadata;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DomainEventID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DomainEvent {

    private DomainEventID id;

    private LocalDateTime dateTime;

    private String eventData;

    public DomainEvent(String eventData) {
        this.id = Identificator.newInstance(DomainEventID.class);
        this.dateTime = LocalDateTime.now();
        this.eventData = eventData;
    }
}
