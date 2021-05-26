package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Demandas;
import org.springframework.beans.factory.annotation.Autowired;

public class MaintainDemanda implements DomainServices {

    @Autowired
    Demandas demandas;

    public void registerNew();

    public void updateDemanda();

    public void deleteDemanda();
}
