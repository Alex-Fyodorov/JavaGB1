package oop.hw7.services;

import oop.hw7.services.converters.Convertering;
import oop.hw7.models.Request;
import oop.hw7.models.calculators.Calculator;
import oop.hw7.models.calculators.WithToBinCalculator;
import oop.hw7.models.createCalc.CreateCalculator;
import oop.hw7.services.validators.Validate;

import java.util.List;

public class Service<T extends Number> {

    private final Calculator<T> calculator;
    private final Convertering<T> converter;

    public Service(CreateCalculator<T> createCalculator) {
        this.calculator = createCalculator.createCalculator();
        this.converter = createCalculator.createConverter();
    }

    /**
     * Метод полностью контролирует обработку данных, полученных от пользователя.     *
     * @param validator Класс, проверяющий данные на валидность.
     * @param request Изначальный запрос от пользователя.
     * @return Строка с ответом.
     */
    public String calculate(Validate validator, Request request) {
        List<T> list = converter.requestToNumbers(request);
        if (!validator.validate(request.getMethod(), list)) {
            return "Введены некорректные данные.";
        }
        return createResponse(request.getMethod(), list, request);
    }

    /**
     * Обработанные данные, полученные от пользователя, данный метод направляет
     * в соответствующий метод калькулятора.
     * @param method Номер метода.
     * @param list Обработанные данные, подготовленные для калькулятора.
     * @param request Изначальный запрос от пользователя.
     * @return Строка с ответом.
     */
    private String createResponse(int method, List<T> list, Request request) {
        String response;
        switch (method) {
            case (0) -> response = null;
            case (5) -> response = ((WithToBinCalculator) calculator)
                    .toBin((Integer) list.get(0), (Integer) list.get(1), request);
            case (1) -> response = calculator.sum(list, request);
            case (2) -> response = calculator.diff(list, request);
            case (3) -> response = calculator.mult(list, request);
            case (4) -> response = calculator.div(list, request);
            default -> response = "Что-то пошло не так.";
        }
        return response;
    }
}
