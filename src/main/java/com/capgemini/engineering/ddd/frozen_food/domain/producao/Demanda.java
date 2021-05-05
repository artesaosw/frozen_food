package com.capgemini.engineering.ddd.frozen_food.domain.producao;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredient;

import javax.validation.constraints.Positive;

//Cada objecto Demanda é apenas de um ingrediente ou conjunto de ingredientes?
public class Demanda {

    private Unit unit;

    private int quantity;

    private Ingredient ingredient;

    public Demanda (Ingredient ingredient, @Positive int quantity, Unit unit){
        this.quantity= quantity;
        this.unit = unit;
        this.ingredient = ingredient;

    }

    //Como aplico padrao observer em stocks?
    public void addDemanda(Demanda demanda){

    };

    public void removeDemanda(Demanda demanda)

}
