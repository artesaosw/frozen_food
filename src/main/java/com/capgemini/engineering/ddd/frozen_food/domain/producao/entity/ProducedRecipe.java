package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipe;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.Status;
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
public class ProducedRecipe implements AggregateRoot, Serializable {

    //ID da Receita Produzida
    private String recipeID;

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

    private Status status;

    private static final String primaryRecipe = "PRIMARY";
    private static final String secondaryRecipe = "SECONDARY";

    //Just to support ORM frameworks
    protected ProducedRecipe() {}

    //Prazo de validade pode ser determinado na criacao da receita e automatizado na producao atraves da adicao do tempo a data de producao
    //atualizacao do lote é manual ou automatico?
    public ProducedRecipe(@NotNull Unit unit, @Positive int quantity, @NotBlank String recipeID, LocalDate prazoValidade,@NotBlank String tipoReceita){
        this.id = Identificator.newInstance(BatchID.class);
        this.unit = unit;
        this.quantity = quantity;
        this.recipeID = recipeID;
        this.prazoValidade = prazoValidade;
        this.tipoReceita = tipoReceita;
        setLocalDate();
        setOpenStatus();
    }

    public void setOpenStatus() {
        this.status = Status.OPEN;
    }

    public void setClosedStatus() {
        this.status = Status.CLOSED;
    }

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
