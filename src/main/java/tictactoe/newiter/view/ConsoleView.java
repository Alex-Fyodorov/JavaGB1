package tictactoe.newiter.view;

import tictactoe.newiter.Map;

import java.util.Scanner;

public class ConsoleView implements View {

    Scanner scanner;
    private Text text;

    public ConsoleView(Text text) {
        this.scanner = new Scanner(System.in);
        this.text = text;
    }

    @Override
    public int inputParam(String message, int min, int max) {
        System.out.println(message);
        int digit = inputDigit();
        while (digit < min || digit > max) {
            System.out.println(text.reInputParam());
            System.out.println(message);
            digit = inputDigit();
        }
        return digit;
    }

    private int inputDigit() {
        String str = scanner.nextLine();
        int digit;
        try {
            digit = Integer.parseInt(str.strip());
            return digit;
        } catch (Exception e) {
            System.out.println(text.notDigit());
            return inputDigit();
        }
    }

    /**
     * Печать поля.
     */
    @Override
    public void printMap() {
        for (int i = 0; i <= Map.size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < Map.size; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < Map.size; j++) {
                System.out.print(Map.map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
