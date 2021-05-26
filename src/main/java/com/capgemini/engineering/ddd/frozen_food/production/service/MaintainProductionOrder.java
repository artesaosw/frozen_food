package com.capgemini.engineering.ddd.frozen_food.production.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.production.DAO.PProductionOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintainProductionOrder implements DomainServices {

    @Autowired
    PProductionOrderDAO PProductionOrderDAO;

}
