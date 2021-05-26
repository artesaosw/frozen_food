package com.capgemini.engineering.ddd.frozen_food.production.DAO;

import com.capgemini.engineering.ddd.frozen_food._shared.id.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.production.valueObject.ProductionOrderState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DemandaDAO extends MongoRepository<Demanda, DemandaID>{

//    public List<Demanda> getAllByProductionOrderState(ProductionOrderState status);

//    public List<Demanda> getAllByLocalDate(LocalDate date);

//    public boolean existsWithProductionOrderState(ProductionOrderState status);
}
