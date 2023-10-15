package oop.hw7.models.calculators;

import oop.hw7.models.Request;
import oop.hw7.models.methods.FourMathOperMethods;
import oop.hw7.models.methods.ToBinMethod;

public class WithToBinCalculator extends Calculator<Integer> {

    private final ToBinMethod toBinMethod;

    public WithToBinCalculator(FourMathOperMethods<Integer> fourMathOperMethods, ToBinMethod toBinMethod) {
        super(fourMathOperMethods);
        this.toBinMethod = toBinMethod;
    }

    /**
     * Принимает число в виде дроби и передаёт его в другой класс для
     * перевода в бинарную форму.
     * @param x Числитель.
     * @param y Знаменатель.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    public String toBin(Integer x, Integer y, Request request) {
        return toBinMethod.toBin(x, y, request);
    }
}
