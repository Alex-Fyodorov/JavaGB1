package oop.hw5.services;

import oop.hw5.converters.Convertering;
import oop.hw5.models.Request;
import oop.hw5.models.calculators.Calculator;
import oop.hw5.models.calculators.WithToBinCalculator;
import oop.hw5.models.createCalc.CreateCalculator;
import oop.hw5.validators.Validate;

import java.util.List;

public class Service<T extends Number> {

    private final Calculator<T> calculator;
    private final Convertering<T> converter;

    public Service(CreateCalculator<T> createCalculator) {
        this.calculator = createCalculator.createCalculator();
        this.converter = createCalculator.createConverter();
    }

    public String calculate(Validate validator, Request request) {
        List<T> list = converter.requestToNumbers(request);
        if (!validator.validate(request.getMethod(), list)) {
            return "Введены некорректные данные.";
        }
        return createResponse(request.getMethod(), list, request);
    }

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
