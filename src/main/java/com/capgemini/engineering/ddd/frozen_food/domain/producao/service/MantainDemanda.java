package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.Producao;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DemandaRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionClosed;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Demandas;

import javax.validation.constraints.NotNull;
import java.util.Map;

//public class MantainDemanda implements DomainServices {
//
//    private Demandas demandas() {
//        return Producao.Demandas();
//    }
//
//    public void registerNew((Map<Ingredient, Integer> articles, Unit unit){
//
//       //Instantiate aggregate
//       Demanda demanda = new Demanda(articles, unit);
//
//        //persists
//        demanda().registerNew(demanda);
//
//        //reports event
//        Events.report(new DemandaRegistered(demanda.getId()));
//    }
//
//    public void updateStatusClosed(@NotNull DemandaID demandaID) {
//        //Validation
//       if (!demandas().existsWithId(demandaID)) {
//            throw new IllegalArgumentException("There is not exists a produced recipe with id = " + batchID.toString());
//        }
//
//        //loads the aggregate instance from DB
//        ProducedRecipe producedRecipe = producedRecipes().withId(batchID);
//
//           //performs domain operation
//        producedRecipe.setClosedStatus();
//
//        //persists
//        producedRecipes().update(producedRecipe);
//
//        //reports event
//        Events.report(new RecipeProductionClosed(batchID));
//    }
//
//    public boolean verifyClosedDemanda(@NotNull DemandaID demandaID) {
//       //instantiate
//        Demanda demanda = demandas().withId(demandaID);
//
//        if (demanda.getStatus().equals("OPEN")) {
//            return true;
//        }
//        return false;
//    }
//}
