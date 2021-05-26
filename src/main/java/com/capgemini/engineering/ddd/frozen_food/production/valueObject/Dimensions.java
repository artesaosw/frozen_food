package com.capgemini.engineering.ddd.frozen_food.production.valueObject;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
public class Dimensions implements AggregateRoot, Serializable {

    @Getter @Setter @NotNull
    private short length;

    @Getter @Setter @NotNull
    private short width;

    @Getter @Setter @NotNull
    private short height;

    @Getter @Setter @NotNull
    private short weight;

    @Override
    public Identificator id() {
        return null;
    }
}
