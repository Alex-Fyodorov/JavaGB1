package oop.hw5.views;

import oop.hw5.models.Request;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements Viewing {

    Scanner scanner = new Scanner(System.in);

    @Override
    public Request createRequest() {
        boolean flag;
        printSystems();
        int system = selectAnithing(2);
        switch (system) {
            case (0) -> {
                return new Request(0, 0, null, null);
            }
            case (1) -> flag = true;
            case (2) -> flag = false;
            default -> {
                printError();
                return new Request(0, 0, null, null);
            }
        }
        printMethods(flag);
        int method = selectAnithing(5);
        switch (method) {
            case (0) -> {
                return new Request(0, 0, null, null);
            }
            case (5) -> {
                System.out.println("Можно вводить целые числа, числа в виде десятичных дробей,\n" +
                        "а также чила в виде рациональной дроби в формате \"а/b\" без пробелов.");
                System.out.println("Введите число для перевода в двоичный вид:");
                return new Request(system, method, scanner.nextLine(), null);
            }
            default -> {
                if (flag) {
                    System.out.println("Можно вводить целые числа, числа в виде десятичных дробей,\n" +
                            "а также чила в виде рациональной дроби в формате \"а/b\" без пробелов.");
                } else {
                    System.out.println("Необходимо вводить комплексные числа в формате \"а+b*i\" " +
                            "без пробелов,\nгде a и b - целые числа или числа в виде десятичных дробей.");
                }
                System.out.println("Введите первое число:");
                String numX = scanner.nextLine();
                System.out.println("Введите второе число:");
                String numY = scanner.nextLine();
                return new Request(system, method, numX, numY);
            }
        }
    }

    @Override
    public void printError() {
        System.out.println("Введены некорректные данные.");
    }

    @Override
    public void printResponse(String str) {
        System.out.println(str);
        System.out.println("===================================================");
    }

    private void printMethods(boolean flag) {
        System.out.println("Выберите действие:");
        System.out.println("1. Сложение.");
        System.out.println("2. Вычитание.");
        System.out.println("3. Умножение.");
        System.out.println("4. Деление.");
        if (flag) System.out.println("5. Перевод в двоичный вид.");
        System.out.println("0. Выход");
    }

    private void printSystems() {
        System.out.println("Выберите, в какой системе вы хотите производить вычисления:");
        System.out.println("1. Обычные числа.");
        System.out.println("2. Комплексные числа.");
        System.out.println("0. Выход");
    }

    private int selectAnithing(int size) {
        int number = -1;
        while (number < 0 || number > size) {
            System.out.println("Введите номер:");
            number = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println();
        return number;
    }

    @Override
    public void printHistory(List<String> history) {
        System.out.println("\nИстория запросов:");
        for (String str : history) {
            System.out.println(str);
        }
        System.out.println("===================================================");
    }
}
