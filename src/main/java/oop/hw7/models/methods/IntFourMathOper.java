package oop.hw7.models.methods;

import oop.hw7.models.Request;
import oop.hw7.models.toString.IntToString;
import oop.hw7.models.toString.ToStringMethod;

import java.util.List;

public class IntFourMathOper implements FourMathOperMethods<Integer> {

    private final ToStringMethod<Integer> toStringMethod;

    public IntFourMathOper(IntToString toStringMethod) {
        this.toStringMethod = toStringMethod;
    }

    /**
     * Метод вычисляет сумму обычных чисел в формате дробей.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String sum(List<Integer> list, Request request) {
        if (!list.get(3).equals(list.get(1))) {
            list.set(0, list.get(0) * list.get(3));
            list.set(2, list.get(2) * list.get(1));
            list.set(1, list.get(1) * list.get(3));
        }
        return toStringMethod.createStringFromEndNumbers(list.get(0) + list.get(2), list.get(1), request);
    }

    /**
     * Метод вычисляет разность обычных чисел в формате дробей.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String diff(List<Integer> list, Request request) {
        list.set(2, -1 * list.get(2));
        return sum(list, request);
    }

    /**
     * Метод вычисляет произведение обычных чисел в формате дробей.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String mult(List<Integer> list, Request request) {
        return toStringMethod.createStringFromEndNumbers(list.get(0) * list.get(2), list.get(1) * list.get(3), request);
    }

    /**
     * Метод вычисляет частное обычных чисел в формате дробей.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String div(List<Integer> list, Request request) {
        return toStringMethod.createStringFromEndNumbers(list.get(0) * list.get(3), list.get(1) * list.get(2), request);
    }
}
