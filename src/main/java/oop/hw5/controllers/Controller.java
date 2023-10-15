package oop.hw5.controllers;

import oop.hw5.loggers.CalcLogger;
import oop.hw5.models.Request;
import oop.hw5.models.createCalc.CreateComplCalc;
import oop.hw5.models.createCalc.CreateIntCalc;
import oop.hw5.services.Service;
import oop.hw5.validators.Validate;
import oop.hw5.views.Viewing;

public class Controller {

    private final Viewing view;
    private final Validate validator;
    private Service<?> service;
    CalcLogger logger;


    public Controller(Viewing view, Validate validator, CalcLogger logger) {
        this.view = view;
        this.validator = validator;
        service = null;
        this.logger = logger;
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            Request request = view.createRequest();
            switch (request.getCalculator()) {
                case (0) -> {
                    flag = false;
                    view.printHistory(logger.getHistory());
                    continue;
                }
                case (1) -> {
                    service = new Service<Integer>(new CreateIntCalc());
                }
                case (2) -> {
                    service = new Service<Double>(new CreateComplCalc());
                }
                default -> {
                    view.printError();
                    continue;
                }
            }
            String response = service.calculate(validator, request);
            if (response == null) {
                flag = false;
                view.printHistory(logger.getHistory());
                continue;
            }
            logger.addNote(response);
            view.printResponse(response);
        }
    }
}
