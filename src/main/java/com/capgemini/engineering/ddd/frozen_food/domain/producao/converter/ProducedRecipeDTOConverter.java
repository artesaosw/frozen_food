package com.capgemini.engineering.ddd.frozen_food.domain.producao.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.producao.dto.ProducedRecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;

public class ProducedRecipeDTOConverter {

    public ProducedRecipeDTO producedRecipe2ProducedRecipeDTO(ProducedRecipe producedRecipe){
        ProducedRecipeDTO producedRecipeDTO = new ProducedRecipeDTO();

        producedRecipeDTO.setRecipeID(producedRecipe.getRecipeID());
        producedRecipeDTO.setDataProducao(producedRecipe.getDataProducao());
        producedRecipeDTO.setId(producedRecipe.getId());
        producedRecipeDTO.setName(producedRecipe.getName());
        producedRecipeDTO.setQuantity(producedRecipe.getQuantity());
        producedRecipeDTO.setTipoReceita(producedRecipe.getTipoReceita());
        producedRecipeDTO.setPrazoValidade(producedRecipe.getPrazoValidade());
        producedRecipeDTO.setUnit(producedRecipe.getUnit());

        return  producedRecipeDTO;
    }
}
