package com.capgemini.engineering.ddd.frozen_food.domain.producao.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.producao.dto.ProducedRecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.valueObject.PackageCharacteristics;

//estimated Time in Days não irá ser necessario enviar aquando o fecho de com.capgemini.engineering.ddd.frozen_food.domain._shared.producao do lote
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
        producedRecipeDTO.setPackaging(new PackageCharacteristics(producedRecipe.getPackaging().getLength(), producedRecipe.getPackaging().getWidth(),
                producedRecipe.getPackaging().getHeight(), producedRecipe.getPackaging().getWeight()));
        producedRecipeDTO.setEstimatedTimeInDays(producedRecipe.getEstimatedTimeInDays());

        return  producedRecipeDTO;
    }
}
