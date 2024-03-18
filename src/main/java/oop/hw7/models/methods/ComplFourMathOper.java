package oop.hw7.models.methods;

import oop.hw7.models.Request;
import oop.hw7.models.toString.ComplToString;
import oop.hw7.models.toString.ToStringMethod;

import java.util.List;

public class ComplFourMathOper implements FourMathOperMethods<Double> {

    private final ToStringMethod<Double> toStringMethod;

    public ComplFourMathOper(ComplToString toStringMethod) {
        this.toStringMethod = toStringMethod;
    }

    /**
     * Метод вычисляет сумму комплексных чисел.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String sum(List<Double> list, Request request) {
        double numA = list.get(0) + list.get(2);
        double numB = list.get(1) + list.get(3);
        return toStringMethod.createStringFromEndNumbers(numA, numB, request);
    }

    /**
     * Метод вычисляет разность комплексных чисел.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String diff(List<Double> list, Request request) {
        Double numA = list.get(0) - list.get(2);
        Double numB = list.get(1) - list.get(3);
        return toStringMethod.createStringFromEndNumbers(numA, numB, request);
    }

    /**
     * Метод вычисляет произведение комплексных чисел.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String mult(List<Double> list, Request request) {
        Double numA = list.get(0) * list.get(2) - list.get(1) * list.get(3);
        Double numB = list.get(0) * list.get(3) + list.get(1) * list.get(2);
        return toStringMethod.createStringFromEndNumbers(numA, numB, request);
    }

    /**
     * Метод вычисляет частное комплексных чисел.
     * @param list Список чисел непосредственно для вычисления.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    @Override
    public String div(List<Double> list, Request request) {
        Double numA = list.get(0) * list.get(2) + list.get(1) * list.get(3);
        Double numB = list.get(1) * list.get(2) - list.get(0) * list.get(3);
        Double numC = list.get(2) * list.get(2) + list.get(3) * list.get(3);
        return toStringMethod.createStringFromEndNumbers(numA / numC, numB / numC, request);
    }
}
