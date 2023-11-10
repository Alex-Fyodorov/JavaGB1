package oop.hw7.models.methods;

import oop.hw7.models.Request;

import java.util.List;

public interface FourMathOperMethods<T extends Number> {

    /**
     * Метод вычисляет сумму.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    String sum(List<T> list, Request request);

    /**
     * Метод вычисляет разность.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    String diff(List<T> list, Request request);

    /**
     * Метод вычисляет произведение.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    String mult(List<T> list, Request request);

    /**
     * Метод вычисляет частное.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    String div(List<T> list, Request request);

}
