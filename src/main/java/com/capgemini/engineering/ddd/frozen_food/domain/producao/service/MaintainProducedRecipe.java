package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Producao;
import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionCanceled;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionClosed;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.IllegalStatusException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.ProducedRecipes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class MantainProducedRecipe implements DomainServices {

    private ProducedRecipes producedRecipes() {
        return Producao.producedRecipes();
    }

    public void registerNew(@NotNull Unit unit, String name, @Positive int quantity, @NotBlank RecipeID recipeID, LocalDate prazoValidade, @NotBlank String tipoReceita) {

        //Instantiate aggregate
        ProducedRecipe producedRecipe = new ProducedRecipe(unit, name, quantity, recipeID, prazoValidade, tipoReceita);

        //persists
        producedRecipes().registerNew(producedRecipe);

        //reports event
        Events.report(new RecipeProductionRegistered(producedRecipe.getId()));
    }

    //Modifica status da Receita Produzida
    public void updateStatusClosed(@NotNull BatchID batchID) {
        //Validation
        if (!producedRecipes().existsWithId(batchID)) {
            throw new IllegalArgumentException("There is not exists a produced recipe with id = " + batchID.toString());
        }

        //loads the aggregate instance from DB
        ProducedRecipe producedRecipe = producedRecipes().withId(batchID);

        //performs domain operation
        producedRecipe.setClosedStatus();

        //persists
        producedRecipes().update(producedRecipe);

        //reports event
        Events.report(new RecipeProductionClosed(batchID));
    }

    public void updateStatusCanceled(@NotNull BatchID batchID) {
        //Validation
        if (!producedRecipes().existsWithId(batchID)) {
            throw new IllegalArgumentException("There is not exists a produced recipe with id = " + batchID.toString());
        }

        //validates object
        if (!verifyClosedRecipe(batchID)) {
            throw new IllegalStatusException("Batch" + batchID.toString() + "This batch is already Closed or Canceled");
        }

        //loads the aggregate instance from DB
        ProducedRecipe producedRecipe = producedRecipes().withId(batchID);

        //performs domain operation
        producedRecipe.setCanceledStatus();

        //persists
        producedRecipes().update(producedRecipe);

        //reports event
        Events.report(new RecipeProductionCanceled(batchID));
    }

    public boolean verifyClosedRecipe(@NotNull BatchID batchID) {
        //instantiate
        ProducedRecipe producedRecipe = producedRecipes().withId(batchID);

        if (producedRecipe.getStatus().equals("OPEN")) {
            return true;
        }
        return false;
    }
}



