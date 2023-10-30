package exceptions.hw1;

public class Main {
    public static void main(String[] args) {

    }


    public static String expr(char a) throws Exception {
// Введите свое решение ниже
        if (a == ' ') {
            throw new RuntimeException("Пустая строка введена.");
        }
        return String.format("Result: Your input was - %c", a);
    }
}
