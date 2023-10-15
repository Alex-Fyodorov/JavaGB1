package oop.hw7.models.createCalc;

import oop.hw7.services.converters.ComplConverter;
import oop.hw7.services.converters.Convertering;
import oop.hw7.models.calculators.Calculator;
import oop.hw7.models.methods.ComplFourMathOper;
import oop.hw7.models.toString.ComplToString;

public class CreateComplCalc implements CreateCalculator<Double> {

    /**
     * Метод формирует объект калькулятор для работы с комплексными числами.
     * @return Calculator<Double>
     */
    @Override
    public Calculator<Double> createCalculator() {
        return new Calculator<>(new ComplFourMathOper(new ComplToString()));
    }

    /**
     * Метод формирует объект конвертер для перевода комплексного числа в список Double.
     * @return Convertering<Double>
     */
    @Override
    public Convertering<Double> createConverter() {
        return new ComplConverter();
    }
}
