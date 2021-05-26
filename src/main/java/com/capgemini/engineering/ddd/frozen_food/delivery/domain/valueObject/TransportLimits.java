package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class TransportLimits {

    /**
     * Unit used for measuring weight
     *
     * @param weightUnit sets the unit for measuring weight (pre-defined as Kilogram)
     * @return the current unit used for measuring weight
     */
    @Getter @Setter
    private Units weightUnit = Units.KG;

    /**
     * Minimum weight for using a particular means of transportation
     *
     * @param minimumWeight sets the minimum weight value for using a particular means of transportation
     * @return the minimum weight value for using a particular means of transportation
     */
    @Getter @Setter
    private float minimumWeight;

    /**
     * Maximum weight allowed for using a particular means of transportation
     *
     * @param maximumWeight sets the maximum weight value for using a particular means of transportation
     * @return the maximum weight value for using a particular means of transportation
     */
    @Getter @Setter
    private float maximumWeight;

    /**
     * Unit used for measuring volume
     *
     * @param volumeUnit sets the unit for measuring volume (pre-defined as Liter)
     * @return the current unit used for measuring volume
     */
    @Getter @Setter
    private Units volumeUnit = Units.L;

    /**
     * Maximum volume allowed for using a particular means of transportation
     *
     * @param maximumVolume sets the maximum volume value for using a particular means of transportation
     * @return the maximum volume value for using a particular means of transportation
     */
    @Getter @Setter
    private float maximumVolume;

    /**
     * Unit used for measuring length
     *
     * @param lengthUnit sets the unit for measuring length (pre-defined as Centimeter)
     * @return the current unit used for measuring length
     */
    @Getter @Setter
    private Units lengthUnit = Units.CM;

    /**
     * Maximum height allowed for using a particular means of transportation
     *
     * @param maximumHeight sets the maximum height value for using a particular means of transportation
     * @return the maximum height value for using a particular means of transportation
     */
    @Getter @Setter
    private float maximumHeight;

    /**
     * Maximum Width allowed for using a particular means of transportation
     *
     * @param maximumWidth sets the maximum width value for using a particular means of transportation
     * @return the maximum width value for using a particular means of transportation
     */
    @Getter @Setter
    private float maximumWidth;

}
