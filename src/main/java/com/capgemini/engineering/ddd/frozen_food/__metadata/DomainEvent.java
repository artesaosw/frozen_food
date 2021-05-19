package com.capgemini.engineering.ddd.frozen_food.__metadata;

import com.capgemini.engineering.ddd.frozen_food._shared.DomainEventID;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DomainEvent {

    private DomainEventID id;

    private LocalDateTime dateTime;

    public DomainEvent() {
        this.id = Identificator.newInstance(DomainEventID.class);
        this.dateTime = LocalDateTime.now();
    }
}
