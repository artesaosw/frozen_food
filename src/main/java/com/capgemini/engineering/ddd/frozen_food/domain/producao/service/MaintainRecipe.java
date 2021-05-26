package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Recipes;
import org.springframework.beans.factory.annotation.Autowired;

public class MaintainRecipe implements DomainServices {

    @Autowired
    Recipes recipes;
}
