package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredient;

import javax.validation.constraints.Positive;
import java.io.Serializable;

//tenho de implementar map portion
//Cada objecto Demanda é apenas de um ingrediente ou conjunto de ingredientes?
public class Demanda implements AggregateRoot, Serializable {

    private DemandaID dID;

    private RecipeID id;

    private Unit unit;

    private int quantity;

    private Ingredient ingredient;

    private String status;

    private static final String Open = "OPEN";
    private static final String Closed = "CLOSED";

    public Demanda (Ingredient ingredient, @Positive int quantity, Unit unit){
        this.id = Identificator.newInstance(DemandaID.class);
        this.quantity= quantity;
        this.unit = unit;
        this.ingredient = ingredient;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public Identificator id() {
        return this.id;
    }
}
