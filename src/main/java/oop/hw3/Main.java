package oop.hw3;

import oop.hw3.alphabets.JapanGame;
import oop.hw3.alphabets.NumberGame;
import oop.hw3.alphabets.RussianGame;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            Game game = selectAlphabet();
            game.start(5, 2);
            Scanner scanner = new Scanner(System.in);
            while (game.getGameStatus() != GameStatus.WIN && game.getGameStatus() != GameStatus.LOSE) {
                System.out.println(game.inputValue(scanner.nextLine()));
            }
            System.out.println(game.getGameStatus());
            flag = endOfGame(game);
        }
    }

    public static Game selectAlphabet() {
        System.out.println("1. Numbers.");
        System.out.println("2. Русский алфавит.");
        System.out.println("3. 片仮名");
        System.out.println("Enter a number from 1 to 3 to select a game.");
        Game game = null;
        Scanner scanner = new Scanner(System.in);
        while (game == null) {
            String index = scanner.nextLine();
            switch (index) {
                case ("1") -> game = new NumberGame();
                case ("2") -> game = new RussianGame();
                case ("3") -> game = new JapanGame();
                default -> System.out.println("Введите число от 1 до 3 для выбора игры.");
            }
        }
        System.out.println("The game has started.");
        return game;
    }

    public static boolean endOfGame(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIf you want to print history of game press \"Y\".");
        if (scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")) {
            for (String str : game.getHistory()) {
                System.out.println(str);
            }
        }
        System.out.println("\nIf you want to game again press \"Y\".");
        return scanner.nextLine().toLowerCase(Locale.ROOT).equals("y");
    }
}
