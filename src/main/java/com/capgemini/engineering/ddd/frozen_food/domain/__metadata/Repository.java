package com.capgemini.engineering.ddd.frozen_food.domain.__metadata;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;

import java.util.List;

public interface Repository<T extends AggregateRoot, V extends Identificator> {

    List<T> all();

    T withId(V id);

    boolean existsWithId(V id);

    void registerNew(T aggregateRoot);

    void update(T aggregateRoot);

}