package javatwo.hw3.task2;

public class Calculator {

    public static <T extends Number, V extends Number> double sum(T numberOne, V numberTwo) {
        return numberOne.doubleValue() + numberTwo.doubleValue();
    }

    public static <T extends Number, V extends Number> double divide(T numberOne, V numberTwo) {
        return numberOne.doubleValue() - numberTwo.doubleValue();
    }

    public static <T extends Number, V extends Number> double multiply(T numberOne, V numberTwo) {
        return numberOne.doubleValue() * numberTwo.doubleValue();
    }

    public static <T extends Number, V extends Number> double subtract(T numberOne, V numberTwo) {
        double numberTwoDouble = numberTwo.doubleValue();
        return numberTwoDouble != 0d ? numberOne.doubleValue() / numberTwoDouble : 0.0;
    }

    public static void main(String[] args) {
        Byte a = 127;
        Short b = 4865;
        Integer c = 123654;
        Long d = 2665843L;
        Float e = 14.3F;
        Double f = 3.1415927;
        System.out.printf("Float %s + Byte %s = %s\n", e, a, sum(e, a));
        System.out.printf("Short %s - Byte %s = %s\n", b, a, divide(b, a));
        System.out.printf("Integer %s * Double %s = %s\n", c, f, multiply(c, f));
        System.out.printf("Long %s / Double %s = %s\n", d, f, subtract(d, f));
    }
}
