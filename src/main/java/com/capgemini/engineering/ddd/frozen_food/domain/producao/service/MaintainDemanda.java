package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Demandas;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MaintainDemanda implements DomainServices {

    @Autowired
    Demandas demandas;

    public Demanda getDemandaById(DemandaID id){
        return null;
    }

    public List<Demanda> getAllByStatus(ProductionOrderState status) { return demandas.getAllByStatus(status);}

    public List<Demanda> getAllByDate(LocalDate date) { return demandas.getAllByDate(date);}

    public List<Demanda> getAllIngredients(){
        return demandas.findAll();
    }


    public void registerNew();

    public void updateIngredients(DemandaID id, @NotEmpty Map<Ingredient, Integer> orders );

    public void updateStatus(DemandaID id, ProductionOrderState status);

    public void deleteDemanda(DemandaID id);
}
