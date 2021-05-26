package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@Document(collection= "demanda_producao")
public class Demanda implements Serializable, AggregateRoot {

    @Id
    private DemandaID id;

    @Setter
    private Map<String, Integer> articles;

    @NotNull
    @Setter
    private ProductionOrderState status;

    @NotNull
    private LocalDate dataDemanda;

    public Demanda(){
        this.id = Identificator.newInstance(DemandaID.class);
        this.articles = new HashMap<>();
    }

    public void setDataDemanda(){
        LocalDate now = LocalDate.now();
        this.dataDemanda= now;
    }

    @Override
    public Identificator id() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }
}
