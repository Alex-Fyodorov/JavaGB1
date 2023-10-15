package oop.hw5.models.calculators;

import lombok.RequiredArgsConstructor;
import oop.hw5.models.Request;
import oop.hw5.models.methods.FourMathOperMethods;

import java.util.List;

@RequiredArgsConstructor
public class Calculator<T extends Number> {

    private final FourMathOperMethods<T> fourMathOperMethods;

    public String sum(List<T> list, Request request) {
        return fourMathOperMethods.sum(list, request);
    }

    public String diff(List<T> list, Request request) {
        return fourMathOperMethods.diff(list, request);
    }

    public String mult(List<T> list, Request request) {
        return fourMathOperMethods.mult(list, request);
    }

    public String div(List<T> list, Request request) {
        return fourMathOperMethods.div(list, request);
    }
}
