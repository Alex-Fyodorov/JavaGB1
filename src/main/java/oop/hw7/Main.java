package oop.hw7;

import oop.hw7.controllers.Controller;
import oop.hw7.models.loggers.GeneralLogger;
import oop.hw7.services.validators.GeneralValidator;
import oop.hw7.views.ConsoleView;

public class Main {

    /**
     * Запуск приложения.
     */
    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(),
                new GeneralValidator(), new GeneralLogger());
        controller.start();
    }
}
