package oop.hw5.models.calculators;

import oop.hw5.models.Request;
import oop.hw5.models.methods.FourMathOperMethods;
import oop.hw5.models.methods.ToBinMethod;

public class WithToBinCalculator extends Calculator<Integer> {

    private final ToBinMethod toBinMethod;

    public WithToBinCalculator(FourMathOperMethods<Integer> fourMathOperMethods, ToBinMethod toBinMethod) {
        super(fourMathOperMethods);
        this.toBinMethod = toBinMethod;
    }

    public String toBin(Integer x, Integer y, Request request) {
        return toBinMethod.toBin(x, y, request);
    }
}
