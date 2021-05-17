package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class MantainProducedRecipes implements DomainServices {

    private ProducedRecipes producedRecipes(){
        return Domain.producedRecipes();
    }

    public void registerNew(@NotNull Unit unit, @Positive int quantity, @NotBlank String recipeID, LocalDate prazoValidade, @NotBlank String tipoReceita){

        //
    }

    //Modifica status da Receita Produzida
    public void closeProducedRecipe(BatchID batchID){}

    public boolean verifyClosedRecipe(BatchID batchID){}


}
