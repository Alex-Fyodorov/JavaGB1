package control;

import control.controller.Controller;
import control.converter.Converter;
import control.getter.Getter;
import control.view.ConsoleView;
import control.writer.Writer;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(),
                new Converter(), new Getter(), new Writer());
        controller.start();
    }
}
