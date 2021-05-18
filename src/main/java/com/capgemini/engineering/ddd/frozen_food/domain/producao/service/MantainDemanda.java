package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.Producao;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DemandaRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Demandas;

import java.util.Map;

public class MantainDemanda implements DomainServices {

    private Demandas demanda() {
        return Producao.demanda();
    }

    public void registerNew((Ingredient ingredient, Map<Ingredient, Integer> articles, Unit unit){

        //Instantiate aggregate
        Demanda demanda = new Demanda(articles, unit);

        //persists
        demanda().registerNew(demanda);

        //reports event
        Events.report(new DemandaRegistered(demanda.getId()));
    }

    public void closeDemanda(DemandaID demandaID){}
}
