package oop.hw5.models.createCalc;

import oop.hw5.converters.Convertering;
import oop.hw5.models.calculators.Calculator;

public interface CreateCalculator<T extends Number> {

    Calculator<T> createCalculator();
    Convertering<T> createConverter();

}
