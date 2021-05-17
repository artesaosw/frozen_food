package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipes;

public class MantainProducedRecipes implements DomainServices {

    private ProducedRecipes recipes(){
        return Domain.recipes();
    }

    public void registerNew(){};

    //Modifica status da Receita Produzida
    public void closeProducedRecipe(BatchID batchID){}

    public boolean verifyClosedRecipe(BatchID batchID){}


}
