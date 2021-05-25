package com.capgemini.engineering.ddd.frozen_food.delivery.domain;

public class Util {

    public static final String POSTALCODE_REGEX = "[1-9][0-9]{3}-[0-9]{3}";

    public static final String CELLPHONE_REGEX = "[9][0-9]{4}-[0-9]{4}";

    public static final String DATE_FORMAT = "dd/mm/yyyy";

    /**
     * Messages
     */

    public static final String POSTALCODE_VALIDATION = "This is an invalid format for a Portuguese Postal Code. Please enter a" +
            "number with the format XXXX-XXX.";

    public static final String EMAIL_VALIDATION = "This is an invalid format for an email! Please enter an email as the" +
            "model: name@domain.com";

    public static final String PHONE_VALIDATION = "This is an invalid format for a cellphone number. Please enter a " +
            "number 9XXXX-XXXX";

    public static final String NAME_VALIDATION = "A name should be at least 3 digits long!";

    public static final String NIF_VALIDATION = "A NIF should have 9 digits long!";


}
