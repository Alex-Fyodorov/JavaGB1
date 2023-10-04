package oop.hw4;

import java.util.List;

public class Calculator {

    public double sum(List<? extends Number> list) {
        double result = 0d;
        for (Number number : list) {
            result += number.doubleValue();
        }
        return result;
    }

    public double mult(Number a, Number b) {
        return a.doubleValue() * b.doubleValue();
    }

    public double div(Number a, Number b) {
        return a.doubleValue() / b.doubleValue();
    }

    public Double toBin(String number) {
        if (isNumber(number)) {
            return toBin(Double.parseDouble(number));
        }
        return null;
    }

    public double toBin(Number number) {
        int intNum = number.intValue();
        double fractionNum = number.doubleValue() - (double) intNum;
        return (double) intToBin(intNum) + fractionToBin(fractionNum);
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

    private boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
