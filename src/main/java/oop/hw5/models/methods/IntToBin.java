package oop.hw5.models.methods;

import oop.hw5.models.Request;

public class IntToBin implements ToBinMethod{

    @Override
    public String toBin(Integer numX, Integer numY, Request request) {
        double number = numX.doubleValue()/numY;
        int intNum = (int) number;
        double fractionNum = number - (double) intNum;
        return request.getNumX() + " = " + ((double) intToBin(intNum) + fractionToBin(fractionNum));
    }

    private long intToBin(int num) {
        long index = 1;
        long result = 0;
        while (num > 0) {
            result += (num % 2) * index;
            num /= 2;
            index *= 10;
        }
        return result;
    }

    private double fractionToBin(double num) {
        double index = 0.1;
        double result = 0d;
        while (num != 0 || index > 0.000000000001) {
            num *= 2;
            if (num >= 1) {
                num -= 1;
                result += index;
            }
            index /= 10;
        }
        return result;
    }
}
