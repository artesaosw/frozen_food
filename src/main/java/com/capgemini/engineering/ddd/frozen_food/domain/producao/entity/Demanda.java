package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.SetStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
//Duvidas no mapper
//tenho de implementar map portion
//Cada objecto Demanda é apenas de um ingrediente ou conjunto de ingredientes?
//String name?
public class Demanda implements AggregateRoot, Serializable, SetStatus {

    private DemandaID dID;

    //Unidade de Producao
    private Unit unit;

    private Map<Ingredient, Integer> articles;

    //Status de Demanda
    private Status status;

    //Date de Demanda
    private LocalDate dataDemanda;

    private static final String Open = "OPEN";
    private static final String Closed = "CLOSED";

    public Demanda (Map<Ingredient, Integer> articles, Unit unit){
        this.dID = Identificator.newInstance(DemandaID.class);
        this.unit = unit;
        this.articles = articles;
        setOpenStatus();
    }

    @Override
    public void setOpenStatus() {
        this.status = Status.OPEN;
    }
    @Override
    public void setClosedStatus() {
        this.status = Status.CLOSED;
    }
    @Override
    public void setCanceledStatus() {
        this.status = Status.CANCELED;
    }

    @Override
    public Identificator id() {
        return this.dID;
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }


}
