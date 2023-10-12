package oop.hw5;

import oop.hw5.controllers.Controller;
import oop.hw5.loggers.GeneralLogger;
import oop.hw5.validators.GeneralValidator;
import oop.hw5.views.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(),
                new GeneralValidator(), new GeneralLogger());
        controller.start();
    }
}
