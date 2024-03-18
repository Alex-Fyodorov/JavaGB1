package oop.hw7.models.createCalc;

import oop.hw7.services.converters.Convertering;
import oop.hw7.services.converters.IntConverter;
import oop.hw7.models.calculators.Calculator;
import oop.hw7.models.calculators.WithToBinCalculator;
import oop.hw7.models.methods.IntFourMathOper;
import oop.hw7.models.methods.IntToBin;
import oop.hw7.models.toString.IntToString;

public class CreateIntCalc implements CreateCalculator<Integer> {

    /**
     * Метод формирует объект калькулятор для работы с обычными числами.
     * @return Calculator<Integer>
     */
    @Override
    public Calculator<Integer> createCalculator() {
        return new WithToBinCalculator(new IntFourMathOper(new IntToString()), new IntToBin());
    }

    /**
     * Метод формирует объект конвертер для перевода любого числа в список целых чисел.
     * @return Convertering<Integer>
     */
    @Override
    public Convertering<Integer> createConverter() {
        return new IntConverter();
    }
}
