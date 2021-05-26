package com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DemandaDAO extends MongoRepository<Demanda, DemandaID>{

    public List<Demanda> getAllByProductionOrderState(ProductionOrderState status);

    public List<Demanda> getAllByLocalDate(LocalDate date);

    public boolean existsWithProductionOrderState(ProductionOrderState status);
}
