package com.capgemini.engineering.ddd.frozen_food.delivery.notinuse;

import java.lang.Math;

public class CreditCard {

    private long number;
    
    public boolean checkLuhn(long number){
        
        boolean check = false;
        String num = number +"";
        int size = num.length();

        int[] ccNum = new int[size];
        double exp;
        for(int i = 0; i < size; i++){
            exp = (double) (i+1);
            ccNum[size-i] = (int) (number % (Math.pow(10,exp)));
        }

        for(int i = 0; i < size;i++){
            if(i % 2 == 0){
                int doubleValue = ccNum[i];
                ccNum[i] = doubleValue*2;
            }
        }

        int[] ccNumRevSum = new int[size];
        for(int i = 0; i < size; i++){
            int sumValue = ccNum[i];
            ccNumRevSum[i] = (sumValue/10 + sumValue%10);
        }

        int sum = 0;
        for(int i = 0; i < size; i++){
            sum = sum + ccNumRevSum[i];
        }

        if(sum % 10 == 0){
            check =true;
        }
        return check;
    }

    
}
