package com.capgemini.engineering.ddd.frozen_food.__metadata;

public interface AggregateRoot extends Entity {
    @Override
    default boolean isEqualsTo(Object other) {
        return Entity.super.isEqualsTo(other);
    }

    @Override
    default int hashcode() {
        return Entity.super.hashcode();
    }
}
