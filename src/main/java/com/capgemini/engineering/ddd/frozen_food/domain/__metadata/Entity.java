package com.capgemini.engineering.ddd.frozen_food.domain.__metadata;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;

public interface Entity {

    Identificator id();

    default boolean isEqualsTo(Object other){
        if (other == null){
            return false;
        }
        if (!this.getClass().isAssignableFrom(other.getClass())){
            return false;
        }
        Entity otherEntity = (Entity) other;
        return this.id().equals(((Entity) other).id());
    }

    default int hashcode(){
        return this.id().hashCode();
    }

}
