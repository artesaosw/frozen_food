package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipe;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.RecipeRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.RecipeProductionRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class MantainProducedRecipe implements DomainServices {

    private ProducedRecipes producedRecipes(){
        return Domain.producedRecipes();
    }

    public void registerNew(@NotNull Unit unit, @Positive int quantity, @NotBlank String recipeID, LocalDate prazoValidade, @NotBlank String tipoReceita){

        //Instantiate aggregate
        ProducedRecipe producedRecipe = new ProducedRecipe(unit,quantity,recipeID,prazoValidade,tipoReceita);

        //persists
        producedRecipes().registerNew(producedRecipe);

        //reports event
        Events.report(new RecipeProductionRegistered(producedRecipe.getId());
    }

    //Modifica status da Receita Produzida
    public void updateStatus(@NotNull BatchID batchID){

    }

    public boolean verifyClosedRecipe(@NotNull BatchID batchID){

    }



}
