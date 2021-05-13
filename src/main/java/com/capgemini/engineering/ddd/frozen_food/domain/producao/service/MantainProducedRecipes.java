package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;

public class MantainProducedRecipes implements DomainServices {

    public void registerNew(){};

    //Modifica status da Receita Produzida
    public void closeProducedRecipe(BatchID batchID){}

}
