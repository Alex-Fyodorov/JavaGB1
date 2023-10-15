package oop.hw5.models.createCalc;

import oop.hw5.converters.Convertering;
import oop.hw5.converters.IntConverter;
import oop.hw5.models.calculators.Calculator;
import oop.hw5.models.calculators.WithToBinCalculator;
import oop.hw5.models.methods.IntFourMathOper;
import oop.hw5.models.methods.IntToBin;
import oop.hw5.models.methods.IntToString;

public class CreateIntCalc implements CreateCalculator<Integer> {

    @Override
    public Calculator<Integer> createCalculator() {
        return new WithToBinCalculator(new IntFourMathOper(new IntToString()), new IntToBin());
    }

    @Override
    public Convertering<Integer> createConverter() {
        return new IntConverter();
    }
}
