package com.capgemini.engineering.ddd.frozen_food.domain.producao.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface Demandas extends MongoRepository<Demanda, DemandaID>{

    public List<Demanda> getAllByStatus(ProductionOrderState status);

    public List<Demanda> getAllByDate(LocalDate date);

    public boolean existsWithStatus(ProductionOrderState status);
}
