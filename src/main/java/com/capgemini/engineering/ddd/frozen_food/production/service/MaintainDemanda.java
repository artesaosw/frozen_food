package com.capgemini.engineering.ddd.frozen_food.production.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.production.DAO.DemandaDAO;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.production.exceptions.AlreadyExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.production.exceptions.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.production.valueObject.ProductionOrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//falta usar DTO e reportar evento
@Service
public class MaintainDemanda implements DomainServices {

    @Autowired
    DemandaDAO demandaDAO;

    public Demanda getDemandaById(DemandaID id){
        return null;
    }

//    public List<Demanda> getAllByStatus(ProductionOrderState status) { return demandaDAO.getAllByProductionOrderState(status);}

//    public List<Demanda> getAllByDate(LocalDate date) { return demandaDAO.getAllByLocalDate(date);}

    public List<Demanda> getAllIngredients(){
        return demandaDAO.findAll();
    }


    public void registerNew(DemandaID id, Map<String, Integer> articles, OrderStatus status) throws AlreadyExistentEntityException {
        if(demandaDAO.existsById(id)){
            throw new AlreadyExistentEntityException("A demanda com o id:"+id+" já existe");
        }
        Demanda demanda = new Demanda();
        demanda.setDataDemanda();
        demanda.setArticles(articles);
        demanda.setStatus(status);

        demandaDAO.save(demanda);
    }

    public void updateIngredients(DemandaID id, Map<String, Integer> orders ) throws NonExistentEntityException {
        if(!demandaDAO.existsById(id)){
            throw new NonExistentEntityException("A demanda com o id:"+id+" não existe");
        }

        Demanda demanda = demandaDAO.findById(id).get();
        demanda.setArticles(orders);

        demandaDAO.save(demanda);
    }

    public void updateStatus(DemandaID id, OrderStatus status) throws NonExistentEntityException{
        if(!demandaDAO.existsById(id)){
            throw new NonExistentEntityException("A demanda com o id:"+id+" não existe");
        }

        Demanda demanda = demandaDAO.findById(id).get();
        demanda.setStatus(status);

        demandaDAO.save(demanda);
    }

    public void deleteDemanda(DemandaID id) throws NonExistentEntityException{
        if(!demandaDAO.existsById(id)){
            throw new NonExistentEntityException("A demanda com o id:"+id+" não existe");
        }

        Demanda demanda = demandaDAO.findById(id).get();
        demandaDAO.delete(demanda);

    }
}
