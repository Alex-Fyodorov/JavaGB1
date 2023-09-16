package sem1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Homework {
    public static void main(String[] args) {
        //printPrimeNums();
        //System.out.println(calculate('+', 3, 2));
        //System.out.println(parseString("aaaabbbcdd"));
        //System.out.println(isPalindrom("А роза упала на лапу Азора"));
        System.out.println(getSolution("file.txt"));
    }

    /**
     * Напишите функцию printPrimeNums, которая выведет на экран все простые
     * числа в промежутке от 1 до 1000, каждое на новой строке.
     * <p>
     * Напишите свой код в методе printPrimeNums класса Answer.
     */
    public static void printPrimeNums() {
        boolean flag = true;
        for (int i = 1; i < 1000; i++) {
            flag = true;
            for (int j = 2; j <= i / 2 + 1; j++) {
                //System.out.println("" + i + " " + j);
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag || i == 2) {
                System.out.println(i);
            }
        }
    }

    /**
     * Напишите класс Calculator, который будет выполнять математические
     * операции (+, -, *, /) над двумя числами и возвращать результат.
     * В классе должен быть метод calculate, который принимает оператор
     * и значения аргументов и возвращает результат вычислений.
     * <p>
     * При неверно переданном операторе, программа должна вывести
     * сообщение об ошибке "Некорректный оператор: 'оператор'".
     */
    public static int calculate(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                System.out.printf("Некорректный оператор: %c", op);
        }
        return 0;
    }

    /**
     * 📔 Напишите метод, который сжимает строку. Пример: вход aaaabbbcdd.
     */
    public static String parseString(String str) {
        int count = 0;
        char ch = str.charAt(0);
        String result = "" + ch;
        for (int i = 0; i < str.length(); i++) {
            if (ch == str.charAt(i)) {
                count++;
            } else {
                result = result + count + str.charAt(i);
                count = 1;
                ch = str.charAt(i);
            }
        }

        result = result + count;
        return result;
    }

    /**
     * Напишите метод, который принимает на вход строку (String)
     * и определяет является ли строка палиндромом (возвращает boolean значение).
     */
    public static boolean isPalindrom(String str) {
        String newString = str.replace(" ", "").toLowerCase();
        StringBuilder sb = new StringBuilder(newString).reverse();
        return newString.equals(sb.toString());
    }

    /**
     * В файле задано уравнение вида q + w = e (q, w, e >= 0).
     * Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
     * Восстановите выражение до верного равенства.
     * Предложите хотя бы одно решение или сообщите, что его нет.
     * Напишите класс Equation, содержащий метод getSolution, который будет
     * считывать уравнение из файла и восстановит его до верного равенства.
     * Выведите сначала строку формата "Given the equation: {выражение из файла}".
     * А затем верните строку формата "Result: {цельное выражение}".
     * Если выражение не имеет решений - верните строку "No solution".
     */
    public static String getSolution(String str) {
        String equation = readFile(str).strip();
        System.out.printf("Given the equation: %s", equation);
        System.out.println();
        List<String> partsOfEquation = devideEquation(equation);
        return getFinalString(partsOfEquation);
    }

    public static String getFinalString(List<String> partsOfEquation) {
        int numbers = (int) Math.pow(10, partsOfEquation.size() - 1);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < numbers; i++) {
            index = i;
            sb.append(partsOfEquation.get(0));
            for (int j = 1; j < partsOfEquation.size(); j++) {
                sb.append(index % 10);
                index /= 10;
                sb.append(partsOfEquation.get(j));
            }
            if (checkOfEquation(sb.toString())) {
                sb.insert(0, "Result: ");
                return sb.toString();
            }
            sb.delete(0, sb.length());
        }
        return "No solution";
    }

    public static List<String> devideEquation(String equation) {
        int index = 0;
        List<String> partsOfEquation = new ArrayList<>();
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '?') {
                partsOfEquation.add(equation.substring(index, i));
                index = i + 1;
            }
        }
        partsOfEquation.add(equation.substring(index));
        return partsOfEquation;
    }

    public static boolean checkOfEquation(String str) {
        String[] array = str.split(" ");
        int a = Integer.parseInt(array[0]);
        int b = Integer.parseInt(array[2]);
        int c = Integer.parseInt(array[4]);
        return a + b == c;
    }

    public static String readFile(String str) {
        File file = new File(str);
        String line = "";
        try (BufferedReader in = new BufferedReader(new FileReader(str))){
            line = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
