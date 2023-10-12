package oop.hw5.models.methods;

import oop.hw5.models.Request;

import java.util.List;

public class IntFourMathOper implements FourMathOperMethods<Integer> {

    private final ToStringMethod<Integer> toStringMethod;

    public IntFourMathOper(IntToString toStringMethod) {
        this.toStringMethod = toStringMethod;
    }

    @Override
    public String sum(List<Integer> list, Request request) {
        if (!list.get(3).equals(list.get(1))) {
            list.set(0, list.get(0) * list.get(3));
            list.set(2, list.get(2) * list.get(1));
            list.set(1, list.get(1) * list.get(3));
        }
        return toStringMethod.createStringFromEndNumbers(list.get(0) + list.get(2), list.get(1), request);
    }

    @Override
    public String diff(List<Integer> list, Request request) {
        list.set(2, -1 * list.get(2));
        return sum(list, request);
    }

    @Override
    public String mult(List<Integer> list, Request request) {
        return toStringMethod.createStringFromEndNumbers(list.get(0) * list.get(2), list.get(1) * list.get(3), request);
    }

    @Override
    public String div(List<Integer> list, Request request) {
        return toStringMethod.createStringFromEndNumbers(list.get(0) * list.get(3), list.get(1) * list.get(2), request);
    }
}
