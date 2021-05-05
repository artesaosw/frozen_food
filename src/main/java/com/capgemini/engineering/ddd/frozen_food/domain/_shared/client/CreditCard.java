package com.capgemini.engineering.ddd.frozen_food.domain._shared.client;

import java.lang.Math;

public class CreditCard {

    private long number;

    String num = number +"";
    int size = num.length();

    int[] ccNum = new int[size];
    double exp;
    for(int i = 0; i < size; i++){
        exp = (double) (i+1);
        ccNum[size-i] = number % Math.pow(10,exp);    
    }
}
