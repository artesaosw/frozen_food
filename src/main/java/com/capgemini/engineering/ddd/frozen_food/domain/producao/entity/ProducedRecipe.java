package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipe;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.Status;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.valueObject.PackageCharacteristics;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
//Necessita estimativa temporal?
//Needs String of reference?
public class ProducedRecipe implements AggregateRoot, Serializable, SetStatus {

    //Duvida aqui
    //ID da Receita Produzida
    private RecipeID recipeID;

    //nome da Receita
    private String name;

    //ID do Lote Produzido
    private BatchID id;

    //Unidade de Producao
    private Unit unit;

    //Quantidade Produzida
    private int quantity;

    //Prazo de Validade
    private LocalDate prazoValidade;

    //Date de Producao
    private LocalDate dataProducao;

    //Tipo de Receita
    private String tipoReceita;

    //Status de Producao
    private Status status;

    //Caracteristicas do pacote
    private PackageCharacteristics packaging;

    //Estimativa de Producao em dias
    private int estimatedTimeInDays;

    private static final String primaryRecipe = "PRIMARY";
    private static final String secondaryRecipe = "SECONDARY";

    //Just to support ORM frameworks
    protected ProducedRecipe() {}

    //Prazo de validade pode ser determinado na criacao da receita e automatizado na producao atraves da adicao do tempo a data de producao
    public ProducedRecipe(@NotNull Unit unit,@NotNull String name, @Positive int quantity, @NotBlank RecipeID recipeID, LocalDate prazoValidade,@NotBlank String tipoReceita, @NotNull PackageCharacteristics packaging, int estimatedTimeInDays){
        this.id = Identificator.newInstance(BatchID.class);
        this.unit = unit;
        this.name = name;
        this.recipeID = recipeID;
        this.prazoValidade = prazoValidade;
        this.tipoReceita = tipoReceita;
        this.packaging = packaging;
        this.estimatedTimeInDays = estimatedTimeInDays;
        setLocalDate();
        setOpenStatus();
    }

    //Interface?
    @Override
    public void setOpenStatus() {
        this.status = Status.OPEN;
    }
    @Override
    public void setClosedStatus() {
        this.status = Status.CLOSED;
    }
    @Override
    public void setCanceledStatus() {
        this.status = Status.CANCELED;
    }

    public void setLocalDate(){
        this.dataProducao = LocalDate.now();
    }

    @Override
    public Identificator id() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }



}
