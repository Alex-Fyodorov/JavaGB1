package oop.sem4.task2;

import java.util.List;

public class Calculator {

    public double sum(List<? extends Number> list) {
        double result = 0d;
        for (Number number : list) {
            result += number.doubleValue();
        }
        return result;
    }
}
