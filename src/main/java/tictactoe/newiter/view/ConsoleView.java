package tictactoe.newiter.view;

import java.util.Scanner;

public class ConsoleView implements View {

    Scanner scanner = new Scanner(System.in);

    @Override
    public int inputFieldsSize() {
        return 0;
    }

    @Override
    public int inputWinsSize() {
        return 0;
    }

    @Override
    public void printField() {

    }
}
