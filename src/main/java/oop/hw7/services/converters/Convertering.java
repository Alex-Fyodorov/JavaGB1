package oop.hw7.services.converters;

import oop.hw7.models.Request;

import java.util.List;

public interface Convertering<T extends Number> {

    /**
     * Метод занимается переводом строк, введённых пользователем,
     * в числа, с которыми уже будет работать калькулятор.
     * @param request Экземпляр запроса.
     * @return Список чисел для калькулятора.
     */
    List<T> requestToNumbers(Request request);
}
