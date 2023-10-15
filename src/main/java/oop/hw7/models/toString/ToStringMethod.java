package oop.hw7.models.toString;

import oop.hw7.models.Request;

public interface ToStringMethod<T extends Number> {

    /**
     * Метод переводит ответ, полученный от калькулятора в строковую форму.
     * @param x Первое число.
     * @param y Первое число.
     * @return Ответ в виде строки.
     */
    String createAnswer(T x, T y);

    /**
     * Метод формирует итоговый ответ на запрос пользователя в виде строки.
     * @param x Первое число, полученное от калькулятора.
     * @param y Первое число, полученное от калькулятора.
     * @param request Изначальный запрос.
     * @return Конечный ответ в виде изначального условия и ответа, записанный в виде строки.
     */
    default String createStringFromEndNumbers(T x, T y, Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getNumX());
        switch (request.getMethod()) {
            case (1) -> sb.append(" + ");
            case (2) -> sb.append(" - ");
            case (3) -> sb.append(" * ");
            case (4) -> sb.append(" / ");
        }
        sb.append(request.getNumY())
                .append(" = ")
                .append(createAnswer(x, y));
        return sb.toString();
    }
}
