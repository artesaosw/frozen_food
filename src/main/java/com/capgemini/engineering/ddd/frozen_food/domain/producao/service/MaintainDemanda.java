package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.AlreadyExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Demandas;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//falta usar DTO e reportar evento
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


    public void registerNew(DemandaID id, Map<Ingredient, Integer> articles, ProductionOrderState status) throws AlreadyExistentEntityException{
        if(demandas.existsById(id)){
            throw new AlreadyExistentEntityException("A demanda com o id:"+id+" já existe");
        }
        Demanda demanda = new Demanda();
        demanda.setDataDemanda();
        demanda.setArticles(articles);
        demanda.setStatus(status);

        demandas.save(demanda);
    }

    public void updateIngredients(DemandaID id, @NotEmpty Map<Ingredient, Integer> orders ) throws NonExistentEntityException {
        if(!demandas.existsById(id)){
            throw new NonExistentEntityException("A demanda com o id:"+id+" não existe");
        }

        Demanda demanda = demandas.findById(id).get();
        demanda.setArticles(orders);

        demandas.save(demanda);
    }

    public void updateStatus(DemandaID id, ProductionOrderState status) throws NonExistentEntityException{
        if(!demandas.existsById(id)){
            throw new NonExistentEntityException("A demanda com o id:"+id+" não existe");
        }

        Demanda demanda = demandas.findById(id).get();
        demanda.setStatus(status);

        demandas.save(demanda);
    }

    public void deleteDemanda(DemandaID id) throws NonExistentEntityException{
        if(!demandas.existsById(id)){
            throw new NonExistentEntityException("A demanda com o id:"+id+" não existe");
        }

        Demanda demanda = demandas.findById(id).get();
        demandas.delete(demanda);

    }
}
