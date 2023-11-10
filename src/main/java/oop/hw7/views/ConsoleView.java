package oop.hw7.views;

import oop.hw7.models.Request;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements Viewing {

    Scanner scanner = new Scanner(System.in);

    /**
     * Метод помогает пользователю создать изначальный запрос.
     * @return Изначальный запрос от пользователя.
     */
    @Override
    public Request createRequest() {
        boolean flag;
        printSystems();
        int system = selectAnything(2);
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
        int method = selectAnything(5);
        switch (method) {
            case (0) -> {
                return new Request(0, 0, null, null);
            }
            case (5) -> {
                System.out.println("Можно вводить целые числа, числа в виде десятичных дробей\n" +
                        "(в качестве запятой используется точка),\n" +
                        "а также числа в виде рациональной дроби в формате \"а/b\" без пробелов.");
                System.out.println("Введите число для перевода в двоичный вид:");
                return new Request(system, method, scanner.nextLine(), null);
            }
            default -> {
                if (flag) {
                    System.out.println("Можно вводить целые числа, числа в виде десятичных дробей\n" +
                            "(в качестве запятой используется точка),\n" +
                            "а также числа в виде рациональной дроби в формате \"а/b\" без пробелов.");
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

    /**
     * Вывод в консоль сообщения об ошибке.
     */
    @Override
    public void printError() {
        System.out.println("Введены некорректные данные.");
    }

    /**
     * Вывод в консоль ответа на запрос.
     * @param str Строка с ответом.
     */
    @Override
    public void printResponse(String str) {
        System.out.println(str);
        System.out.println("===================================================");
    }

    /**
     * Вывод в консоль списка методов калькулятора.
     * @param flag Индикатор, показывающий, нужно ли включать в меню метод
     *             перевода числа в бинарный вид.
     */
    private void printMethods(boolean flag) {
        System.out.println("Выберите действие:");
        System.out.println("1. Сложение.");
        System.out.println("2. Вычитание.");
        System.out.println("3. Умножение.");
        System.out.println("4. Деление.");
        if (flag) System.out.println("5. Перевод в двоичный вид.");
        System.out.println("0. Выход");
    }

    /**
     * Вывод в консоль списка видов калькуляторов.
     */
    private void printSystems() {
        System.out.println("Выберите, в какой системе вы хотите производить вычисления:");
        System.out.println("1. Обычные числа.");
        System.out.println("2. Комплексные числа.");
        System.out.println("0. Выход");
    }

    /**
     * Метод, позволяющий выбрать один пункт из списка.
     * @param size Размер списка.
     * @return Выбранный пункт.
     */
    private int selectAnything(int size) {
        int number = -1;
        while (number < 0 || number > size) {
            System.out.println("Введите номер:");
            number = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println();
        return number;
    }

    /**
     * Вывод в консоль истории запросов.
     * @param history Список строк, содержащих информацию о запросе и ответе на него.
     */
    @Override
    public void printHistory(List<String> history) {
        System.out.println("\nИстория запросов:");
        for (String str : history) {
            System.out.println(str);
        }
        System.out.println("===================================================");
    }
}
