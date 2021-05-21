package com.capgemini.engineering.ddd.frozen_food._shared;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Entity;
import com.capgemini.engineering.ddd.frozen_food.__metadata.MetadataException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public interface Identificator {

    UUID id();

    default boolean isEqualsTo(Object other){
        if (other == null){
            return false;
        }
        if (!this.getClass().isAssignableFrom(other.getClass())){
            return false;
        }
        Identificator otherIdentificator = (Identificator) other;
        return this.id().equals(((Entity) other).id());
    }

    default int hashcode(){
        return id().hashCode();
    }

    static <T extends Identificator> T newInstance(@NotNull Class clazz){
        if (!Identificator.class.isAssignableFrom(clazz)){
            throw new IllegalArgumentException("Argument \"clazz\" does not extend Identificator");
        }
        try {
            return (T) clazz
                    .getConstructor()
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MetadataException(e);
        }
    }

    static <T extends Identificator> T newInstance(@NotNull Class clazz, @NotBlank String id){
        if (!Identificator.class.isAssignableFrom(clazz)){
            throw new IllegalArgumentException("Argument \"clazz\" does not extend Identificator");
        }
        try {
            return (T) clazz
                    .getConstructor(new Class[]{String.class})
                    .newInstance(id);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MetadataException(e);
        }
    }

    static <T extends Identificator> T clone(@NotNull T identificator){
        UUID id = UUID.fromString(identificator.id().toString());
        try {
            return (T) identificator
                    .getClass()
                    .getConstructor(UUID.class)
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new MetadataException(e);
        }
    }
}
