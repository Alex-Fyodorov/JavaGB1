package oop.hw5.models.methods;

import oop.hw5.models.Request;

import java.util.List;

public class ComplFourMathOper implements FourMathOperMethods<Double> {

    private final ToStringMethod<Double> toStringMethod;

    public ComplFourMathOper(ComplToString toStringMethod) {
        this.toStringMethod = toStringMethod;
    }

    @Override
    public String sum(List<Double> list, Request request) {
        double numA = list.get(0) + list.get(2);
        double numB = list.get(1) + list.get(3);
        return toStringMethod.createStringFromEndNumbers(numA, numB, request);
    }

    @Override
    public String diff(List<Double> list, Request request) {
        Double numA = list.get(0) - list.get(2);
        Double numB = list.get(1) - list.get(3);
        return toStringMethod.createStringFromEndNumbers(numA, numB, request);
    }

    @Override
    public String mult(List<Double> list, Request request) {
        Double numA = list.get(0) * list.get(2) - list.get(1) * list.get(3);
        Double numB = list.get(0) * list.get(3) + list.get(1) * list.get(2);
        return toStringMethod.createStringFromEndNumbers(numA, numB, request);
    }

    @Override
    public String div(List<Double> list, Request request) {
        Double numA = list.get(0) * list.get(2) + list.get(1) * list.get(3);
        Double numB = list.get(1) * list.get(2) - list.get(0) * list.get(3);
        Double numC = list.get(2) * list.get(2) + list.get(3) * list.get(3);
        return toStringMethod.createStringFromEndNumbers(numA / numC, numB / numC, request);
    }
}
