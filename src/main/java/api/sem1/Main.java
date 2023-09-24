package api.sem1;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //helloUser();
        //reverseWords("А роза упала на лапу Азора");
        //power();
    }

    /**
     * 📔 **Текст задачи:**
     * В консоли запросить имя пользователя.
     * В зависимости от текущего времени, вывести приветствие вида
     * <p>
     * "Доброе утро, <Имя>!", если время от 05:00 до 11:59
     * "Добрый день, <Имя>!", если время от 12:00 до 17:59
     * "Добрый вечер, <Имя>!", если время от 18:00 до 22:59
     * "Доброй ночи, <Имя>!", если время от 23:00 до 4:59
     */
    public static void helloUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your name: ");
        String name = scanner.nextLine();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();

        if (hour >= 5 && hour < 12) {
            System.out.printf("Доброе утро, %s!", name);
        } else if (hour >= 12 && hour < 18) {
            System.out.printf("Добрый день, %s!", name);
        } else if (hour >= 18 && hour < 23) {
            System.out.printf("Добрый вечер, %s!", name);
        } else {
            System.out.printf("Доброй ночи, %s!", name);
        }
        scanner.close();
    }

    /**
     * Текст задачи:
     * Во фразе "Добро пожаловать на курс по Java"
     * переставить слова в обратном порядке.
     */
    public static void reverseWords(String str) {
        System.out.println(str);
        String[] arr = str.trim().split(" ");
        String newString = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            newString = newString + " " + arr[i];
        }
        System.out.println(newString);
    }

    /**
     * Реализовать функцию возведения числа а в степень b.
     * a, b из Z. Сводя количество выполняемых действий к минимуму.
     * Пример 1: а = 3, b = 2, ответ: 9
     * Пример 2: а = 2, b = -2, ответ: 0.25
     * Пример 3: а = 3, b = 0, ответ: 1
     */
    public static void power() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a: ");
        int a = scanner.nextInt();
        System.out.print("Input b: ");
        int b = scanner.nextInt();
        double result = 1;
        if (b != 0) {
            for (int i = 1; i <= Math.abs(b); i++) {
                result *= (double) a;
            }
        }
        if (b < 0) {
            System.out.println(1 / result);
        } else
            System.out.println(result);
        scanner.close();
    }
}
