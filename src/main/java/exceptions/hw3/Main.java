package exceptions.hw3;

import exceptions.hw3.controllers.Controller;
import exceptions.hw3.savers.FileSaver;
import exceptions.hw3.validators.Validator;
import exceptions.hw3.views.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(
                new ConsoleView(), new Validator(), new FileSaver());
        controller.start();
    }
}
