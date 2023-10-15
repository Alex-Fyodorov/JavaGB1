package oop.hw7.models.methods;

import oop.hw7.models.Request;

public interface ToBinMethod {

    /**
     * Принимает число в виде дроби и переводит его в бинарную форму.
     * @param x Числитель.
     * @param y Знаменатель.
     * @param request Изначальный запрос для формирования строкового представления ответа.
     * @return Строка с ответом.
     */
    String toBin(Integer x, Integer y, Request request);
}
