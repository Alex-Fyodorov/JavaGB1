package oop.hw7.models.createCalc;

import oop.hw7.services.converters.Convertering;
import oop.hw7.models.calculators.Calculator;

public interface CreateCalculator<T extends Number> {

    /**
     * Метод формирует объект калькулятор.
     * @return Calculator
     */
    Calculator<T> createCalculator();

    /**
     * Метод формирует объект конвертер.
     * @return Convertering
     */
    Convertering<T> createConverter();

}
