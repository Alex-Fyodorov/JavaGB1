package oop.hw5.models.createCalc;

import oop.hw5.converters.ComplConverter;
import oop.hw5.converters.Convertering;
import oop.hw5.models.calculators.Calculator;
import oop.hw5.models.methods.ComplFourMathOper;
import oop.hw5.models.methods.ComplToString;

public class CreateComplCalc implements CreateCalculator<Double> {

    @Override
    public Calculator<Double> createCalculator() {
        return new Calculator<>(new ComplFourMathOper(new ComplToString()));
    }

    @Override
    public Convertering<Double> createConverter() {
        return new ComplConverter();
    }
}
