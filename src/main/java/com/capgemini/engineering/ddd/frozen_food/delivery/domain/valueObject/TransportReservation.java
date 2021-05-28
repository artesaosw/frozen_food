package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TransportReservation {

    private LocalDateTime reservationStart;

    private LocalDateTime reservationEnd;

}
