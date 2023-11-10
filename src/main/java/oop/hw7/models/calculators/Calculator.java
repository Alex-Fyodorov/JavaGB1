package oop.hw7.models.calculators;

import lombok.RequiredArgsConstructor;
import oop.hw7.models.Request;
import oop.hw7.models.methods.FourMathOperMethods;

import java.util.List;

@RequiredArgsConstructor
public class Calculator<T extends Number> {

    private final FourMathOperMethods<T> fourMathOperMethods;

    /**
     * Принимает входящие данные и передаёт их в другой класс для
     * вычисления СУММЫ.
     * @param list Список чисел для непосредственного вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    public String sum(List<T> list, Request request) {
        return fourMathOperMethods.sum(list, request);
    }

    /**
     * Принимает входящие данные и передаёт их в другой класс для
     * вычисления РАЗНОСТИ.
     * @param list Список чисел для непосредственного вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    public String diff(List<T> list, Request request) {
        return fourMathOperMethods.diff(list, request);
    }

    /**
     * Принимает входящие данные и передаёт их в другой класс для
     * вычисления ПРОИЗВЕДЕНИЯ.
     * @param list Список чисел для непосредственного вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    public String mult(List<T> list, Request request) {
        return fourMathOperMethods.mult(list, request);
    }

    /**
     * Принимает входящие данные и передаёт их в другой класс для
     * вычисления ЧАСТНОГО.
     * @param list Список чисел для непосредственного вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    public String div(List<T> list, Request request) {
        return fourMathOperMethods.div(list, request);
    }
}
