package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Transport;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.TransportRepository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.TransportStatus;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.TransportID;

import java.util.ArrayList;
import java.util.List;

public class TransportDatabase implements TransportRepository {

    @Override
    public List<Transport> all() {
        return null;
    }

    @Override
    public Transport withId(TransportID id) {
        return null;
    }

    @Override
    public boolean existsWithId(TransportID id) {
        return false;
    }

    @Override
    public void registerNew(Transport aggregateRoot) {

    }

    @Override
    public void update(Transport aggregateRoot) {

    }

    @Override
    public List<Transport> availableTransport(){
        List<Transport> allTransports = all();
        List<Transport> availableOnes = new ArrayList<>();
        for(Transport transport: allTransports){
            if(transport.getTransportStatus().equals(TransportStatus.AVAILABLE)){
                availableOnes.add(transport);
            }
        }
        return availableOnes;
    }
}
