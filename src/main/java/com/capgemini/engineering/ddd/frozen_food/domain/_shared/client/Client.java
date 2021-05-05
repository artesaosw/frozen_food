package com.capgemini.engineering.ddd.frozen_food.domain._shared.client;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.ids.ClientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.*;

import javax.persistence.Id;
import java.io.Serializable;

public class Client implements Serializable {

    @Id
    private ClientID clientID;

    private String name;

    private NIF nif;

    private Address address;

    private PaymentMethod paymentMethod;

}
